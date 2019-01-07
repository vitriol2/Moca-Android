package com.example.parkseeun.moca_android.ui.community.feed.other_user

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetFeedResponse
import com.example.parkseeun.moca_android.model.get.GetFeedResponseData
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.community.feed.ReviewRecyclerViewAdapter
import com.example.parkseeun.moca_android.ui.community.follow.FollowActivity
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_other_user.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtherUserActivity : AppCompatActivity(), View.OnClickListener{
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var getFeedResponse : Call<GetFeedResponse>
    private lateinit var reviewRecyclerViewAdapter : ReviewRecyclerViewAdapter

    override fun onClick(v: View?) {
        when(v){
            other_follower_tv -> {
                Intent(this, FollowActivity::class.java).apply {
                    this.putExtra("flag","follower")
                    startActivity(this)
                }
            }
            other_following_tv -> {
                Intent(this, FollowActivity::class.java).apply {
                    putExtra("flag","following")
                    startActivity(this)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_user)

        other_follower_tv.setOnClickListener(this)
        other_following_tv.setOnClickListener(this)

        communicate()
    }
    // RecyclerView 설정
    private fun communicate() {
        // intent로 받아온 user_id가 없을 경우 유저 아이디 사용
        getFeedResponse = networkService.getUserFeed(User.token, intent.getStringExtra("user_id")?:User.user_id)
        getFeedResponse.enqueue(object : Callback<GetFeedResponse> {
            override fun onFailure(call: Call<GetFeedResponse>?, t: Throwable?) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetFeedResponse>?, response: Response<GetFeedResponse>?) {
                if(response!!.isSuccessful)
                    if (response!!.body()!!.status == 200) {
                        var dataList: ArrayList<GetFeedResponseData> = response.body()!!.data
                        reviewRecyclerViewAdapter = ReviewRecyclerViewAdapter(applicationContext!!, dataList)
                        other_reviews_recycler.adapter = reviewRecyclerViewAdapter
                        other_reviews_recycler.layoutManager = LinearLayoutManager(applicationContext)
                    } else if (response!!.body()!!.status != 204) {
                        toast(response!!.body()!!.status.toString() + ": " + response!!.body()!!.message)
                    }
            }
        })
    }
}
