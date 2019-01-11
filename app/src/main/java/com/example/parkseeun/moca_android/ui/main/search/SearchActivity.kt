package com.example.parkseeun.moca_android.ui.main.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_search.*
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.parkseeun.moca_android.model.get.*
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.main.BeforeSearchPopularCafeAdapter
import com.example.parkseeun.moca_android.ui.main.BeforeSearchRecommendPlaceAdapter
import com.example.parkseeun.moca_android.util.User
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    // RecyclerView 설정
    lateinit var searchResultAdapter: SearchAdapater
    // 통신
    private val networkService = ApplicationController.instance.networkService
    // 검색
    private lateinit var getHomeSearchResponse: Call<GetHomeSearchResponse>
    var dataList: ArrayList<GetHomeSearchResponseData> = ArrayList()
    private var getHomeSearchResponseData = ArrayList<GetHomeSearchResponseData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // 뒤로 가기 버튼
        ib_backBtn_act_search.setOnClickListener {
            finish()
        }
        // tab button
        btn_all_category.setOnClickListener {
            content1.visibility = View.VISIBLE
            content2.visibility = View.GONE
            content3.visibility = View.GONE
            view_all.visibility = View.VISIBLE
            view_cafe.visibility = View.GONE
            view_location.visibility = View.GONE
            btn_all_category.setTextColor(resources.getColor(R.color.colorPrimary))
            btn_cafe_category.setTextColor(resources.getColor(R.color.dark_gray))
            btn_location_category.setTextColor(resources.getColor(R.color.dark_gray))
        }
        btn_all_category.performClick()
        btn_cafe_category.setOnClickListener {
            content1.visibility = View.GONE
            content2.visibility = View.VISIBLE
            content3.visibility = View.GONE
            view_all.visibility = View.GONE
            view_cafe.visibility = View.VISIBLE
            view_location.visibility = View.GONE
            btn_all_category.setTextColor(resources.getColor(R.color.dark_gray))
            btn_cafe_category.setTextColor(resources.getColor(R.color.colorPrimary))
            btn_location_category.setTextColor(resources.getColor(R.color.dark_gray))

        }
        btn_location_category.setOnClickListener {
            content1.visibility = View.GONE
            content2.visibility = View.GONE
            content3.visibility = View.VISIBLE
            view_all.visibility = View.GONE
            view_cafe.visibility = View.GONE
            view_location.visibility = View.VISIBLE
            btn_all_category.setTextColor(resources.getColor(R.color.dark_gray))
            btn_cafe_category.setTextColor(resources.getColor(R.color.dark_gray))
            btn_location_category.setTextColor(resources.getColor(R.color.colorPrimary))
        }

        // 기본 통신 세팅
        getBestCafe(this)
        getMocaRecommendHotPlace(this)

        // 엔터키 처리
        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_search.text.toString() == "") {
                    linear_before_search_all.visibility = View.VISIBLE
                    rv_searchResult_list_all.visibility = View.GONE
                    rv_searchResult_list_cafe.visibility = View.GONE
                    rv_searchResult_list_location.visibility = View.GONE
                } else {
                    linear_before_search_all.visibility = View.GONE
                    getSearchResult(this@SearchActivity, et_search.text.toString())
                }
            }

        })
    }

    // 검색 통신
    private fun getSearchResult(context: Context, searchString: String) {
        getHomeSearchResponse = networkService.getHomeSearch(searchString)
        getHomeSearchResponse.enqueue(object : Callback<GetHomeSearchResponse> {
            override fun onFailure(call: Call<GetHomeSearchResponse>, t: Throwable) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetHomeSearchResponse>, response: Response<GetHomeSearchResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        getHomeSearchResponseData = response.body()!!.data

                        searchResultAdapter = SearchAdapater(context, getHomeSearchResponseData)

                        // 전체 탭
                        rv_searchResult_list_all.visibility = View.VISIBLE
                        rv_searchResult_list_all.adapter = searchResultAdapter
                        rv_searchResult_list_all.layoutManager = LinearLayoutManager(context)

                        // 카페 탭
                        val cafeList = ArrayList<GetHomeSearchResponseData>()

                        for (value in getHomeSearchResponseData) {
                            if (!value.type) {
                                cafeList.add(value)
                            }

                            searchResultAdapter = SearchAdapater(context, cafeList)
                            rv_searchResult_list_cafe.visibility = View.VISIBLE
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
                            rv_searchResult_list_location.visibility = View.VISIBLE
                            rv_searchResult_list_location.adapter = searchResultAdapter
                            rv_searchResult_list_location.layoutManager = LinearLayoutManager(context)
                        }
                    } else {
                        rv_searchResult_list_all.visibility = View.GONE
                        rv_searchResult_list_cafe.visibility = View.GONE
                        rv_searchResult_list_location.visibility = View.GONE
                    }
                }
            }
        })
    }

    // 검색 전 인기 카페 통신
    private fun getBestCafe(context: Context) {
        val getBestCafeListResponse = networkService.getBestCafeList(User.token!!, 0)
        getBestCafeListResponse.enqueue(object : Callback<GetBestCafeListResponse> {
            override fun onFailure(call: Call<GetBestCafeListResponse>, t: Throwable) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetBestCafeListResponse>, response: Response<GetBestCafeListResponse>) {
                if (response.isSuccessful)
                    if (response.body()!!.status == 200) {
                        var getBestCafeListData: ArrayList<GetBestCafeListData> = response.body()!!.data

                        rv_search_popularCafe_all.adapter = BeforeSearchPopularCafeAdapter(context, getBestCafeListData)
                        rv_search_popularCafe_all.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    } else if (response.body()!!.status == 204) {
                        toast("인기 카페가 존재하지 않습니다!")
                    }
            }
        })
    }

    // 검색 전 모카 추천 핫플레이스 통신
    private fun getMocaRecommendHotPlace(context: Context) {
        val getMocaRecommendHotPlaceResponse = networkService.getMocaRecommenPlace()
        getMocaRecommendHotPlaceResponse.enqueue(object : Callback<GetMocaRecommendHotPlaceResponse> {
            override fun onFailure(call: Call<GetMocaRecommendHotPlaceResponse>, t: Throwable) {
                toast(t.message!!.toString())
            }

            override fun onResponse(call: Call<GetMocaRecommendHotPlaceResponse>, response: Response<GetMocaRecommendHotPlaceResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        var recommendList = response.body()!!.data

                        rv_search_recommendPlace_all.adapter = BeforeSearchRecommendPlaceAdapter(context, recommendList)
                        rv_search_recommendPlace_all.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    }
                }
            }
        })
    }
}
