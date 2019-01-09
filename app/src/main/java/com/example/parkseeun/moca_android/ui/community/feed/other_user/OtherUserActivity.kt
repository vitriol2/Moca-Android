package com.example.parkseeun.moca_android.ui.community.feed.other_user

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetFeedResponse
import com.example.parkseeun.moca_android.model.get.GetFeedResponseData
import com.example.parkseeun.moca_android.model.get.GetUserDataResponse
import com.example.parkseeun.moca_android.model.post.PostFollowResponse
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
    private lateinit var getUserDataResponse : Call<GetUserDataResponse>
    private lateinit var postFollowerResponse : Call<PostFollowResponse>
    private lateinit var reviewRecyclerViewAdapter : ReviewRecyclerViewAdapter

    override fun onClick(v: View?) {
        when(v){
            other_back_iv -> finish()
            other_follower_tv -> {
                Intent(this, FollowActivity::class.java).apply {
                    this.putExtra("flag","follower")
                    this.putExtra("user_id", intent.getStringExtra("user_id"))
                    startActivity(this)
                }
            }
            other_following_tv -> {
                Intent(this, FollowActivity::class.java).apply {
                    putExtra("flag","following")
                    this.putExtra("user_id", intent.getStringExtra("user_id"))
                    startActivity(this)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_user)
        // swipe refresh
        other_swipe_sl.setColorSchemeColors(resources.getColor(R.color.colorPrimaryDark))
        other_swipe_sl.setOnRefreshListener {
            communicate()
            other_swipe_sl.isRefreshing = false
        }
        other_back_iv.setOnClickListener(this)
        other_follower_tv.setOnClickListener(this)
        other_following_tv.setOnClickListener(this)

        communicate()
    }

    private fun communicate() {
        // 상단부
        // intent로 받아온 user_id가 없을 경우 유저 아이디 사용
        getUserDataResponse = networkService.getUserData(User.token, intent.getStringExtra("user_id")?:User.user_id)
        getUserDataResponse.enqueue(object : Callback<GetUserDataResponse>{
            override fun onFailure(call: Call<GetUserDataResponse>?, t: Throwable?) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetUserDataResponse>?, response: Response<GetUserDataResponse>?) {
                if (response!!.isSuccessful)
                    if (response!!.body()!!.status == 200) {
                        response.body()!!.data.let {
                            Glide.with(this@OtherUserActivity).load(it.user_img_url).into(other_profile_ci)
                            other_title_tv.text = it.user_name
                            other_name_tv.text = it.user_name
                            other_status_tv.text = it.user_status_comment
                            other_review_tv.text = it.review_count.toString()
                            other_follower_tv.text = it.follower_count.toString()
                            other_following_tv.text = it.following_count.toString()
                            if (it.follow) {
                                other_follow_iv.setImageResource(R.drawable.community_follow_success)
                                other_follow_iv.setOnClickListener {
                                    other_follow_iv.setImageResource(R.drawable.community_otheruser_follow)
                                    followUnfollow(intent.getStringExtra("user_id"))
                                }
                            } else {
                                other_follow_iv.setImageResource(R.drawable.community_otheruser_follow)
                                other_follow_iv.setOnClickListener {
                                    other_follow_iv.setImageResource(R.drawable.community_follow_success)
                                    followUnfollow(intent.getStringExtra("user_id"))
                                }
                            }
                        }
                    }
            }
        })
        // 리뷰들
        getFeedResponse = networkService.getUserFeed(User.token, intent.getStringExtra("user_id")
                ?: User.user_id)
        getFeedResponse.enqueue(object : Callback<GetFeedResponse> {
            override fun onFailure(call: Call<GetFeedResponse>?, t: Throwable?) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetFeedResponse>?, response: Response<GetFeedResponse>?) {
                if (response!!.isSuccessful)
                    if (response!!.body()!!.status == 200) {
                        var dataList: ArrayList<GetFeedResponseData> = response.body()!!.data
                        reviewRecyclerViewAdapter = ReviewRecyclerViewAdapter(applicationContext!!, dataList, intent.getStringExtra("user_id"))
                        other_reviews_recycler.adapter = reviewRecyclerViewAdapter
                        other_reviews_recycler.layoutManager = LinearLayoutManager(applicationContext)
                    } else if (response!!.body()!!.status != 204) {
                        toast(response!!.body()!!.status.toString() + ": " + response!!.body()!!.message)
                    }
            }
        })
    }
    private fun followUnfollow(user_id: String) {
        postFollowerResponse = networkService.postFollow(User.token!!, user_id)
        postFollowerResponse.enqueue(object: Callback<PostFollowResponse> {
            override fun onFailure(call: Call<PostFollowResponse>, t: Throwable) {
                toast(t.message.toString())
            }
            override fun onResponse(call: Call<PostFollowResponse>, response: Response<PostFollowResponse>) {
                if(response.isSuccessful)
                    if(response.body()!!.status == 200)
                        communicate()
                    else
                        toast(response.body()!!.status.toString() + " : " + response.body()!!.message)
            }
        })
    }
}
