package com.example.parkseeun.moca_android.ui.detail.detailReviewAll

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.ranking.RankingData
import kotlinx.android.synthetic.main.activity_review_all.*

class ReviewAllActivity : AppCompatActivity() {

    private val PcafeList = ArrayList<ReviewAllPopularData>()
    private val RcafeList = ArrayList<ReviewAllPopularData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_all)

        setRecyclerView()

        makeList()

        setOnBtnClickListeners()

    }

    private fun setOnBtnClickListeners() {
        iv_act_review_all_back.setOnClickListener {
            finish()
        }
    }

    private fun makeList() {
        PcafeList.add(ReviewAllPopularData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG"))
        PcafeList.add(ReviewAllPopularData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG"))
        PcafeList.add(ReviewAllPopularData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG"))

        for (i in 1..20) {
            RcafeList.add(ReviewAllPopularData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG"))
        }

    }

    private fun setRecyclerView() {
        rv_act_review_all_popular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        rv_act_review_all_popular.adapter = ReviewAllPopularAdapter(this, PcafeList)


        val spanCount = 3 // 3 columns
        val spacing = 40     // 50px
        val includeEdge = false
        rv_act_review_all_recent.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
        rv_act_review_all_recent.layoutManager = GridLayoutManager(this, 3)
//        rv_act_review_all_recent.adapter = ReviewAllPopularAdapter(this, RcafeList)


    }


}
