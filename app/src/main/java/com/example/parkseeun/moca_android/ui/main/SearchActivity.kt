package com.example.parkseeun.moca_android.ui.main

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_search.*
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.widget.*
import java.security.Key
import android.R.attr.host
import android.content.Context
import android.widget.TextView
import android.support.v4.view.MarginLayoutParamsCompat.setMarginEnd
import android.support.v4.view.MarginLayoutParamsCompat.setMarginStart
import android.os.Build
import android.view.ViewGroup
import android.support.design.widget.TabLayout
import android.util.Log
import com.example.parkseeun.moca_android.model.get.*
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.community.follow.FollowData
import com.example.parkseeun.moca_android.util.User
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    // 탭
    private var tabHost: TabHost? = null
    private var tabWidget: TabWidget? = null
    private var frameLayout: FrameLayout? = null

    private var tab1: TabHost.TabSpec? = null
    private var tab2: TabHost.TabSpec? = null
    private var tab3: TabHost.TabSpec? = null

    private var currentTab = 0 // 현재 선택된 탭

    // RecyclerView 설정
    lateinit var searchResultAdapter : SearchAdapater
    lateinit var beforeSearchPopularCafeAdapter: BeforeSearchPopularCafeAdapter
    lateinit var beforeSearchRecommendPlaceAdapter: BeforeSearchRecommendPlaceAdapter

    // 통신
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var getBestCafeListResponse : Call<GetBestCafeListResponse> // 인기 카페 리스트
    // 검색
    private lateinit var getHomeSearchResponse : Call<GetHomeSearchResponse>
    var dataList : ArrayList<GetHomeSearchResponseData> = ArrayList()
    private var getHomeSearchResponseData = ArrayList<GetHomeSearchResponseData>()
    // 모카 추천 핫플레이스
    private lateinit var getMocaRecommendHotPlaceResponse: Call<GetMocaRecommendHotPlaceResponse>
    private var recommendList = ArrayList<GetMocaRecommendHotPlaceData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setUpTab()
        setTabSelected()

        // 뒤로 가기 버튼
        ib_backBtn_act_search.setOnClickListener {
            finish()
        }


        // 엔터키 처리
        et_search.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                //Enter key Action
                return if (event.action === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    linear_before_search_all.visibility = View.GONE
                    linear_before_search_cafe.visibility = View.GONE
                    linear_before_search_location.visibility = View.GONE

                    getSearchResult(v.context, et_search.text.toString())

                    true
                } else false
            }
        })

        // 검색 전 인기 카페, 모카 추천 핫플레이스 설정
        getBestCafe(this)
        getMocaRecommendHotPlace(this)
    }

    // 탭 처리
    private fun setUpTab() {
        tabWidget = TabWidget(this)
        frameLayout = FrameLayout(this)

        tabHost = findViewById(R.id.tabHost)

        tabHost!!.setup()

        tab1 = tabHost!!.newTabSpec("1").setContent(R.id.content1)
            .setIndicator("전체")
        tab2 = tabHost!!.newTabSpec("2").setContent(R.id.content2)
            .setIndicator("카페명")
        tab3 = tabHost!!.newTabSpec("3").setContent(R.id.content3)
            .setIndicator("위치")



        tabHost!!.addTab(tab1)
        tabHost!!.addTab(tab2)
        tabHost!!.addTab(tab3)


        for (i in 0 until tabHost!!.tabWidget.childCount) {
            val tv = tabHost!!.tabWidget.getChildAt(i).findViewById(android.R.id.title) as TextView
            if (i == 0) {
                tv.setTextColor(Color.parseColor("#e1b2a3"))
            }

            tv.setTextColor(Color.parseColor("#e1b2a3"))
        }


    }

    // tab 눌러질 때 처리
    fun setTabSelected() {
        frameLayout = findViewById<View>(android.R.id.tabcontent) as FrameLayout

        tabHost!!.setOnTabChangedListener {
            currentTab = tabHost!!.currentTab

            for (i in 0 until tabHost!!.tabWidget.childCount) {
                // When tab is not selected
                val tv = tabHost!!.tabWidget.getChildAt(i).findViewById(android.R.id.title) as TextView
                tv.setTextColor(Color.parseColor("#707070"))
            }

            var tv = tabHost!!.tabWidget.getChildAt(tabHost!!.currentTab).findViewById(android.R.id.title) as TextView
            tv.setTextColor(Color.parseColor("#e1b2a3"))

        }


    }

    // 검색 전 인기 카페 통신
    private fun getBestCafe(context : Context) {
        getBestCafeListResponse = networkService.getBestCafeList(User.token!!, 0)
        getBestCafeListResponse.enqueue(object: Callback<GetBestCafeListResponse> {
            override fun onFailure(call: Call<GetBestCafeListResponse>, t: Throwable) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetBestCafeListResponse>, response: Response<GetBestCafeListResponse>) {
                if(response.isSuccessful)
                    if(response.body()!!.status==200) {
                        var getBestCafeListData: ArrayList<GetBestCafeListData> = response.body()!!.data

                        beforeSearchPopularCafeAdapter = BeforeSearchPopularCafeAdapter(context, getBestCafeListData)
                        rv_search_popularCafe_all.adapter = beforeSearchPopularCafeAdapter
                        rv_search_popularCafe_all.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                        rv_search_popularCafe_cafe.adapter = beforeSearchPopularCafeAdapter
                        rv_search_popularCafe_cafe.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                        rv_search_popularCafe_location.adapter = beforeSearchPopularCafeAdapter
                        rv_search_popularCafe_location.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    } else if (response.body()!!.status == 204) {
                        toast("인기 카페가 존재하지 않습니다!")
                    }
            }
        })
    }

    // 검색 전 모카 추천 핫플레이스 통신
    private fun getMocaRecommendHotPlace(context : Context) {
        getMocaRecommendHotPlaceResponse = networkService.getMocaRecommenPlace()
        getMocaRecommendHotPlaceResponse.enqueue(object : Callback<GetMocaRecommendHotPlaceResponse> {
            override fun onFailure(call: Call<GetMocaRecommendHotPlaceResponse>, t: Throwable) {
                toast(t.message!!.toString())
            }

            override fun onResponse(call: Call<GetMocaRecommendHotPlaceResponse>, response: Response<GetMocaRecommendHotPlaceResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        recommendList = response.body()!!.data

                        beforeSearchRecommendPlaceAdapter = BeforeSearchRecommendPlaceAdapter(context, recommendList)
                        rv_search_recommendPlace_all.adapter = beforeSearchPopularCafeAdapter
                        rv_search_recommendPlace_all.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                        rv_search_recommendPlace_cafe.adapter = beforeSearchPopularCafeAdapter
                        rv_search_recommendPlace_cafe.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                        rv_search_recommendPlace_location.adapter = beforeSearchPopularCafeAdapter
                        rv_search_recommendPlace_location.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    }
                }
            }

        })
    }

    // 검색 통신
    private fun getSearchResult(context : Context, searchString : String) {
        getHomeSearchResponse = networkService.getHomeSearch(searchString)
        getHomeSearchResponse.enqueue(object: Callback<GetHomeSearchResponse> {
            override fun onFailure(call: Call<GetHomeSearchResponse>, t: Throwable) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetHomeSearchResponse>, response: Response<GetHomeSearchResponse>) {
                if(response.isSuccessful)
                    if(response.body()!!.status==200) {
                        getHomeSearchResponseData = response.body()!!.data

                        searchResultAdapter = SearchAdapater(context, getHomeSearchResponseData)

                        Log.v("검색결과", getHomeSearchResponseData.toString())

                        // 전체 탭
                        rv_searchResult_list_all.adapter = searchResultAdapter
                        rv_searchResult_list_all.layoutManager = LinearLayoutManager(context)

                        // 카페 탭
                        val cafeList = ArrayList<GetHomeSearchResponseData>()

                        for (value in getHomeSearchResponseData) {
                            if (!value.type) {
                                cafeList.add(value)
                            }

                            searchResultAdapter = SearchAdapater(context, cafeList)
                            rv_searchResult_list_cafe.adapter = searchResultAdapter
                            rv_searchResult_list_cafe.layoutManager = LinearLayoutManager(context)
                        }

                        // 위치 탭
                        val locationList = ArrayList<GetHomeSearchResponseData>()

                        for (value in getHomeSearchResponseData) {
                            if (value.type) {
                                locationList.add(value)
                            }

                            searchResultAdapter = SearchAdapater(context, locationList)
                            rv_searchResult_list_location.adapter = searchResultAdapter
                            rv_searchResult_list_location.layoutManager = LinearLayoutManager(context)
                        }
                    } else if (response!!.body()!!.status == 204) {
                        toast("인기 카페가 존재하지 않습니다!")
                    }
            }
        })
    }
}
