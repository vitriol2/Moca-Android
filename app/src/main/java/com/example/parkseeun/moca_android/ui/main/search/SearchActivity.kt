package com.example.parkseeun.moca_android.ui.main.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_search.*
import android.content.Context
import android.view.View
import com.example.parkseeun.moca_android.model.get.*
import com.example.parkseeun.moca_android.network.ApplicationController
import kotlinx.android.synthetic.main.fragment_search_all.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    // RecyclerView 설정
    lateinit var searchResultAdapter: SearchAdapater
    lateinit var pagerAdapter: SearchPagerAdapter
    // 통신
    private val networkService = ApplicationController.instance.networkService
    // 검색
    private lateinit var getHomeSearchResponse: Call<GetHomeSearchResponse>
    var dataList: ArrayList<GetHomeSearchResponseData> = ArrayList()
    private var getHomeSearchResponseData = ArrayList<GetHomeSearchResponseData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // setting viewPager
        pagerAdapter = SearchPagerAdapter(supportFragmentManager) // 프래그먼트안에 뷰페이저 쓸경우 childFragmentManager써주세욤
        val viewPager = search_vp
        val tabLayout = search_tab
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        // 뒤로 가기 버튼
        ib_backBtn_act_search.setOnClickListener {
            finish()
        }


        // 엔터키 처리
        et_search.setOnKeyListener { v, keyCode, event ->
            //Enter key Action
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                getSearchResult(this@SearchActivity, et_search.text.toString())
//                when(pagerAdapter.pos
//                linear_before_search_all.visibility = View.GONE
//                linear_before_search_cafe.visibility = View.GONE
//                linear_before_search_location.visibility = View.GONE
//
//                getSearchResult(v.context, et_search.text.toString())

                true
            } else false
        }
    }

    // 검색 통신
    private fun getSearchResult(context: Context, searchString: String) {
        getHomeSearchResponse = networkService.getHomeSearch(searchString)
        getHomeSearchResponse.enqueue(object : Callback<GetHomeSearchResponse> {
            override fun onFailure(call: Call<GetHomeSearchResponse>, t: Throwable) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetHomeSearchResponse>, response: Response<GetHomeSearchResponse>) {
                if (response.isSuccessful)
                    if (response.body()!!.status == 200) {
                        getHomeSearchResponseData = response.body()!!.data
                        searchResultAdapter = SearchAdapater(context, getHomeSearchResponseData)
                        when (pagerAdapter.pos) {
                            0 -> {
                                linear_before_search_all.visibility = View.GONE
                                // 전체 탭
                                rv_searchResult_list_all.adapter = searchResultAdapter
                                rv_searchResult_list_all.layoutManager = LinearLayoutManager(context)
                            }
                        }

                        // 카페 탭
//                        val cafeList = ArrayList<GetHomeSearchResponseData>()
//
//                        for (value in getHomeSearchResponseData) {
//                            if (!value.type) {
//                                cafeList.add(value)
//                            }
//
//                            searchResultAdapter = SearchAdapater(context, cafeList)
//                            rv_searchResult_list_cafe.adapter = searchResultAdapter
//                            rv_searchResult_list_cafe.layoutManager = LinearLayoutManager(context)
//                        }
//
//                        // 위치 탭
//                        val locationList = ArrayList<GetHomeSearchResponseData>()
//
//                        for (value in getHomeSearchResponseData) {
//                            if (value.type) {
//                                locationList.add(value)
//                            }
//
//                            searchResultAdapter = SearchAdapater(context, locationList)
//                            rv_searchResult_list_location.adapter = searchResultAdapter
//                            rv_searchResult_list_location.layoutManager = LinearLayoutManager(context)
//                        }
                    } else if (response!!.body()!!.status == 204) {
                        toast("인기 카페가 존재하지 않습니다!")
                    }
            }
        })
    }
}
