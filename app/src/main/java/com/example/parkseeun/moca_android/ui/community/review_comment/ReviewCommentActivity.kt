package com.example.parkseeun.moca_android.ui.community.review_comment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.community.follow.FollowData
import com.example.parkseeun.moca_android.ui.community.follow.FollowRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_follow.*
import kotlinx.android.synthetic.main.activity_review_comment.*

class ReviewCommentActivity : AppCompatActivity() {

    // RecyclerView 설정
    lateinit var reviewCommentViewAdapter : ReviewCommentViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_comment)

        setRecyclerView()
    }

    // RecyclerView 설정
    private fun setRecyclerView() {
        // 임시 데이터
        var dataList : ArrayList<ReviewCommentData> = ArrayList()
        dataList.add(ReviewCommentData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "김정환", "댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용댓글 달았어용", "2018.09.20 11:20"))
        dataList.add(ReviewCommentData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "김정환", "댓글 달았어용", "2018.09.20 11:20"))
        dataList.add(ReviewCommentData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "김정환", "댓글 달았어용", "2018.09.20 11:20"))
        dataList.add(ReviewCommentData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "김정환", "댓글 달았어용", "2018.09.20 11:20"))
        dataList.add(ReviewCommentData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "김정환", "댓글 달았어용", "2018.09.20 11:20"))

        reviewCommentViewAdapter = ReviewCommentViewAdapter(applicationContext!!, dataList)
        rv_comment_commentList.adapter = reviewCommentViewAdapter
        rv_comment_commentList.layoutManager = LinearLayoutManager(applicationContext)
    }
}