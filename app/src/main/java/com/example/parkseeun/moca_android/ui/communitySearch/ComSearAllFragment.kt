package com.example.parkseeun.moca_android.ui.communitySearch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.*
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.detail.detailReviewAll.ReviewAllPopularAdapter
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_community_search.*
import kotlinx.android.synthetic.main.fragment_com_sear_all.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComSearAllFragment : Fragment() {

    // 통신
    private val networkService  = ApplicationController.instance.networkService
    // 이번주 리뷰 많은 카페
    private lateinit var getBestCafeListResponse : Call<GetBestCafeListResponse>
    private var getBestCafeListData = ArrayList<GetBestCafeListData>()
    // 이번주 인기 많은 사용자
    private lateinit var getBestUserResponse : Call<GetBestUserResponse>
    private var getBestUserData = ArrayList<GetBestUserData>()
    // 전체
    private var popularReviewList = ArrayList<ReviewData>()
    private var searchUserList = ArrayList<SearchUserData>()

    companion object {
        private var instance : ComSearAllFragment? = null
        @Synchronized
        fun getInstance(popularReviewList: ArrayList<ReviewData>, searchUserList: ArrayList<SearchUserData>) : ComSearAllFragment{
            if(instance == null){
                instance = ComSearAllFragment().apply{
                    arguments = Bundle().apply{
                        putParcelableArrayList("popularReviewList", popularReviewList)
                        putParcelableArrayList("searchUserList", searchUserList)
                    }
                }
            }
            return instance!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_com_sear_all, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        arguments?.let {
            popularReviewList = it.getParcelableArrayList("popularReviewList")
            searchUserList = it.getParcelableArrayList("searchUserList") }


        // 검색 전 화면
        getBestReviewCafe()
        getBestUser()

        // 검색 전 화면 - 이번주 리뷰 많은 카페 RecyclerView 설정
        rv_frag_com_sear_all_reviewtop.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_frag_com_sear_all_reviewtop.adapter = ComSearAllReviewTopAdapter(activity!!, getBestCafeListData)

        // 이번주 인기 많은 사용자
        rv_frag_comm_sear_all_popularuser.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_frag_comm_sear_all_popularuser.adapter = ComSearAllPopUserAdapter(activity!!, getBestUserData)


        // RecyclerView 설정
        rv_frag_com_sear_all_review.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_frag_com_sear_all_review.adapter = ReviewAllPopularAdapter(activity!!, popularReviewList)

        rv_frag_com_sear_all_user.layoutManager = LinearLayoutManager(activity)
        rv_frag_com_sear_all_user.adapter = ComSearUserAdapter(activity!!, searchUserList)

    }



    // 통신 (이번주 리뷰 많은 카페)
    private fun getBestReviewCafe() {
        getBestCafeListResponse = networkService.getBestCafeList(User.token!!, 1)
        getBestCafeListResponse.enqueue(object : Callback<GetBestCafeListResponse> {
            override fun onFailure(call: Call<GetBestCafeListResponse>, t: Throwable) {
                toast(t.message.toString())
            }

            override fun onResponse(call: Call<GetBestCafeListResponse>, response: Response<GetBestCafeListResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        getBestCafeListData = response.body()!!.data

                        // 검색 전 화면 - 이번주 리뷰 많은 카페 RecyclerView 설정
                        rv_frag_com_sear_all_reviewtop.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                        rv_frag_com_sear_all_reviewtop.adapter = ComSearAllReviewTopAdapter(activity!!, getBestCafeListData)
                    }
                }
            }
        })
    }



    // 통신 (이번주 인기 많은 사용자)
    private fun getBestUser() {
        getBestUserResponse = networkService.getBestUserList(User.token!!)
        getBestUserResponse.enqueue(object : Callback<GetBestUserResponse> {
            override fun onFailure(call: Call<GetBestUserResponse>, t: Throwable) {
                toast(t.message.toString())
            }

            override fun onResponse(call: Call<GetBestUserResponse>, response: Response<GetBestUserResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        getBestUserData = response.body()!!.data

                        // 이번주 인기 많은 사용자
                        rv_frag_comm_sear_all_popularuser.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                        rv_frag_comm_sear_all_popularuser.adapter = ComSearAllPopUserAdapter(activity!!, getBestUserData)
                    }
                }
            }
        })
    }


}