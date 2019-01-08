package com.example.parkseeun.moca_android.ui.community.feed

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetFeedResponse
import com.example.parkseeun.moca_android.model.get.GetFeedResponseData
import com.example.parkseeun.moca_android.model.get.GetUserDataResponse
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.community.follow.FollowActivity
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.fragment_my.*
import kotlinx.android.synthetic.main.fragment_my.view.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyFragment :Fragment(), View.OnClickListener{
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var getFeedResponse : Call<GetFeedResponse>
    private lateinit var getMyDataResponse : Call<GetUserDataResponse>
    private lateinit var reviewRecyclerViewAdapter : ReviewRecyclerViewAdapter

    override fun onClick(v: View?) {
        when(v){
            my_follower_tv -> {
                Intent(context, FollowActivity::class.java).apply {
                    putExtra("flag","follower")
                    startActivity(this)
                }
            }
            my_following_tv -> {
                Intent(context, FollowActivity::class.java).apply {
                    putExtra("flag","following")
                    startActivity(this)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_my,container,false)

        v.my_follower_tv.setOnClickListener(this)
        v.my_following_tv.setOnClickListener(this)
        // swipe refresh
        v.my_refresh_sl.setColorSchemeColors(resources.getColor(R.color.colorPrimaryDark))
        v.my_refresh_sl.setOnRefreshListener {
            communicate(v)
            v.my_refresh_sl.isRefreshing = false
        }

        communicate(v)
        return v
    }

    private fun communicate(v:View) {
        // 상단부
        getMyDataResponse = networkService.getUserData(User.token, User.user_id)
        getMyDataResponse.enqueue(object : Callback<GetUserDataResponse>{
            override fun onFailure(call: Call<GetUserDataResponse>?, t: Throwable?) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetUserDataResponse>?, response: Response<GetUserDataResponse>?) {
                if(response!!.isSuccessful)
                    if (response!!.body()!!.status == 200) {
                        Glide.with(context!!).load(response.body()!!.data.user_img_url).into(v.my_profile_ci)
                        v.my_name_tv.text = response.body()!!.data.user_name
                        v.my_status_tv.text = response.body()!!.data.user_status_comment
                        v.my_review_tv.text = response.body()!!.data.review_count.toString()
                        v.my_follower_tv.text = response.body()!!.data.follower_count.toString()
                        v.my_following_tv.text = response.body()!!.data.following_count.toString()
                    } else {
                        toast(response!!.body()!!.status.toString() + ": " + response!!.body()!!.message)
                    }

            }

        })
        // 리뷰들
        getFeedResponse = networkService.getUserFeed(User.token, User.user_id)
        getFeedResponse.enqueue(object : Callback<GetFeedResponse>{
            override fun onFailure(call: Call<GetFeedResponse>?, t: Throwable?) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetFeedResponse>?, response: Response<GetFeedResponse>?) {
                if(response!!.isSuccessful)
                    if (response!!.body()!!.status == 200) {
                        var dataList: ArrayList<GetFeedResponseData> = response.body()!!.data
                        reviewRecyclerViewAdapter = ReviewRecyclerViewAdapter(context!!, dataList, User.user_id)
                        v.my_reviews_recycler.adapter = reviewRecyclerViewAdapter
                        v.my_reviews_recycler.layoutManager = LinearLayoutManager(context)
                    } else if (response!!.body()!!.status != 204) {
                        toast(response!!.body()!!.status.toString() + ": " + response!!.body()!!.message)
                    }
            }
        })
    }
}