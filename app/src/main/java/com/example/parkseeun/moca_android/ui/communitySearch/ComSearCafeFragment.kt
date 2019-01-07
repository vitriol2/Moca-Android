package com.example.parkseeun.moca_android.ui.communitySearch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.detail.detailReviewAll.*
import kotlinx.android.synthetic.main.activity_review_all.*
import kotlinx.android.synthetic.main.activity_review_all.view.*
import kotlinx.android.synthetic.main.fragment_com_sear_all.*
import kotlinx.android.synthetic.main.fragment_com_sear_cafe.*
import kotlinx.android.synthetic.main.fragment_dialog_coupon.*
import kotlinx.android.synthetic.main.navigation_community_search.*

class ComSearCafeFragment : Fragment() {
    private val PcafeList = ArrayList<ReviewAllPopularData>()
    private val RcafeList = ArrayList<ReviewAllRecentData>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_com_sear_cafe, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()

        makeList()
    }

    private fun makeList() {
        PcafeList.add(ReviewAllPopularData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG"))
        PcafeList.add(ReviewAllPopularData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG"))
        PcafeList.add(ReviewAllPopularData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG"))

        for (i in 1..20) {
            RcafeList.add(ReviewAllRecentData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG"))
        }

    }

    private fun setRecyclerView() {
        rv_frag_com_sear_cafe_popular!!.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_frag_com_sear_cafe_popular!!.adapter = ReviewAllPopularAdapter(activity!!, PcafeList)


        val spanCount = 3 // 3 columns
        val spacing = 40     // 50px
        val includeEdge = false
        rv_frag_com_sear_cafe_recent.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
        rv_frag_com_sear_cafe_recent.layoutManager = GridLayoutManager(activity, 3)
        rv_frag_com_sear_cafe_recent.adapter = ReviewAllRecentAdapter(activity!!, RcafeList)


    }
}