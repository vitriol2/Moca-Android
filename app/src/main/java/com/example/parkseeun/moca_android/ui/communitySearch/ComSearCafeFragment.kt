package com.example.parkseeun.moca_android.ui.communitySearch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetBestCafeListData
import com.example.parkseeun.moca_android.model.get.GetCommunitySearchListResponse
import com.example.parkseeun.moca_android.model.get.ReviewData
import com.example.parkseeun.moca_android.model.get.SearchUserData
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.detail.detailReviewAll.*
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_community_search.*
import kotlinx.android.synthetic.main.activity_review_all.*
import kotlinx.android.synthetic.main.activity_review_all.view.*
import kotlinx.android.synthetic.main.fragment_com_sear_all.*
import kotlinx.android.synthetic.main.fragment_com_sear_cafe.*
import kotlinx.android.synthetic.main.fragment_dialog_coupon.*
import kotlinx.android.synthetic.main.navigation_community_search.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComSearCafeFragment : Fragment() {
    // 통신
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var getCommunitySearchListResponse : Call<GetCommunitySearchListResponse>
    private var popularReviewList = ArrayList<ReviewData>()
    private var reviewListOrderByLatest = ArrayList<ReviewData>()

//    companion object {
//        private var instance : ComSearCafeFragment? = null
//        @Synchronized
//        fun getInstance(popularReviewList: ArrayList<ReviewData>, reviewListOrderByLatest : ArrayList<ReviewData>) : ComSearCafeFragment{
//            if(instance == null){
//                instance = ComSearCafeFragment().apply{
//                    arguments = Bundle().apply{
//                        putParcelableArrayList("popularReviewList", popularReviewList)
//                        putParcelableArrayList("reviewListOrderByLatest", reviewListOrderByLatest)
//                    }
//                }
//            }
//            return instance!!
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_com_sear_cafe, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


//        arguments?.let {
//            popularReviewList = it.getParcelableArrayList("popularReviewList")
//            reviewListOrderByLatest = it.getParcelableArrayList("reviewListOrderByLatest") }

        setRecyclerView()

        setScreenConvert()
    }

    private fun setScreenConvert() {
        activity!!.et_act_comm_sear.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    getAllResult(s.toString())

            }
        })
    }

    private fun setRecyclerView() {
        rv_frag_com_sear_cafe_popular!!.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_frag_com_sear_cafe_popular!!.adapter = ReviewAllPopularAdapter(activity!!, popularReviewList)


        val spanCount = 3 // 3 columns
        val spacing = 40     // 50px
        val includeEdge = false
        rv_frag_com_sear_cafe_recent.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
        rv_frag_com_sear_cafe_recent.layoutManager = GridLayoutManager(activity, 3)
        rv_frag_com_sear_cafe_recent.adapter = ReviewAllRecentAdapter(activity!!, reviewListOrderByLatest)
    }

    // 통신 (카페 탭)
    private fun getAllResult(searchString : String) {
        getCommunitySearchListResponse = networkService.getCommunitySearchResult(User.token!!, searchString)
        getCommunitySearchListResponse.enqueue(object : Callback<GetCommunitySearchListResponse> {
            override fun onFailure(call: Call<GetCommunitySearchListResponse>, t: Throwable) {
                toast(t.message.toString())
            }

            override fun onResponse(call: Call<GetCommunitySearchListResponse>, response: Response<GetCommunitySearchListResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        var communitySearchData = response.body()!!.data

                        popularReviewList = communitySearchData.popularReviewList
                        reviewListOrderByLatest = communitySearchData.reviewListOrderByLatest

                        setRecyclerView()
                    }
                }
                else {
                    toast("response failed")
                }
            }
        })
    }
}