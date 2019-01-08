package com.example.parkseeun.moca_android.ui.community.review_comment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetReviewCommentResponse
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_review_comment.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewCommentActivity : AppCompatActivity() {
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var getCommentResponse : Call<GetReviewCommentResponse>
    lateinit var reviewCommentViewAdapter : ReviewCommentViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_comment)

        communicate()
    }

    private fun communicate() {
        getCommentResponse = networkService.getReviewComment(User.token, intent.getIntExtra("review_id", 1))
        getCommentResponse.enqueue(object : Callback<GetReviewCommentResponse> {
            override fun onFailure(call: Call<GetReviewCommentResponse>?, t: Throwable?) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetReviewCommentResponse>?, response: Response<GetReviewCommentResponse>?) {
                if(response!!.isSuccessful)
                    if (response.body()!!.status == 200) {
                        rv_comment_commentList.visibility = View.VISIBLE
                        iv_comment_empty.visibility = View.GONE
                        reviewCommentViewAdapter = ReviewCommentViewAdapter(applicationContext, response.body()!!.data)
                        rv_comment_commentList.adapter = reviewCommentViewAdapter
                        rv_comment_commentList.layoutManager = LinearLayoutManager(applicationContext)
                    } else if (response.body()!!.status != 204) {
                        toast(response.body()!!.status.toString() + ": " + response.body()!!.message)
                    }
            }

        })
    }
}