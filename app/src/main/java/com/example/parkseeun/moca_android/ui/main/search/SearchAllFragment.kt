package com.example.parkseeun.moca_android.ui.main.search

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.*
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.main.BeforeSearchPopularCafeAdapter
import com.example.parkseeun.moca_android.ui.main.BeforeSearchRecommendPlaceAdapter
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.fragment_search_all.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchAllFragment : Fragment(){
    // RecyclerView 설정
    lateinit var beforeSearchPopularCafeAdapter: BeforeSearchPopularCafeAdapter
    lateinit var beforeSearchRecommendPlaceAdapter: BeforeSearchRecommendPlaceAdapter

    // 통신
    private val networkService  = ApplicationController.instance.networkService
    // 인기 카페 리스트
    private lateinit var getBestCafeListResponse : Call<GetBestCafeListResponse>
    // 모카 추천 핫플레이스
    private lateinit var getMocaRecommendHotPlaceResponse: Call<GetMocaRecommendHotPlaceResponse>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_search_all,container,false)
        beforeSearchPopularCafeAdapter = BeforeSearchPopularCafeAdapter(context!!, ArrayList())
        getBestCafe(context!!)
        getMocaRecommendHotPlace(context!!)

        return v
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
                        beforeSearchPopularCafeAdapter = BeforeSearchPopularCafeAdapter(context, response.body()!!.data)
                        rv_search_popularCafe_all.adapter = beforeSearchPopularCafeAdapter
                        rv_search_popularCafe_all.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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
                        beforeSearchRecommendPlaceAdapter = BeforeSearchRecommendPlaceAdapter(context, response.body()!!.data)
                        rv_search_recommendPlace_all.adapter = beforeSearchRecommendPlaceAdapter
                        rv_search_recommendPlace_all.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    }
                }
            }
        })
    }
}