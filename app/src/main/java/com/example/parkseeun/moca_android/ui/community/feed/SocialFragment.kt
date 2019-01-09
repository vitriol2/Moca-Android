package com.example.parkseeun.moca_android.ui.community.feed

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetFeedResponse
import com.example.parkseeun.moca_android.model.get.GetFeedResponseData
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.fragment_social.*
import kotlinx.android.synthetic.main.fragment_social.view.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SocialFragment :Fragment(){
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var getFeedResponse : Call<GetFeedResponse>
    lateinit var reviewRecyclerViewAdapter : ReviewRecyclerViewAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_social,container,false)
        // swipe refresh
        v.social_refresh_sl.setColorSchemeColors(resources.getColor(R.color.colorPrimaryDark))
        v.social_refresh_sl.setOnRefreshListener {
            communicate(v)
            v.social_refresh_sl.isRefreshing = false
        }
        communicate(v)
        return v
    }
    // RecyclerView 설정
    private fun communicate(v:View) {
        getFeedResponse = networkService.getSocialFeed(User.token)
        getFeedResponse.enqueue(object : Callback<GetFeedResponse> {
            override fun onFailure(call: Call<GetFeedResponse>?, t: Throwable?) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetFeedResponse>?, response: Response<GetFeedResponse>?) {
                if(response!!.isSuccessful)
                    if (response!!.body()!!.status == 200) {
                        var dataList: ArrayList<GetFeedResponseData> = response.body()!!.data
                        reviewRecyclerViewAdapter = ReviewRecyclerViewAdapter(context!!, dataList)
                        social_reviews_recycler.adapter = reviewRecyclerViewAdapter
                        social_reviews_recycler.layoutManager = LinearLayoutManager(context)
                        social_empty_const.visibility = View.GONE
                    } else if (response!!.body()!!.status == 204) {
                        social_empty_const.visibility = View.VISIBLE
                    } else {
                        toast(response!!.body()!!.status.toString() + ": " + response!!.body()!!.message)
                    }
            }
        })
    }
}