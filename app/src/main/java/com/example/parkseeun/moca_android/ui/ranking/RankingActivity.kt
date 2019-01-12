package com.example.parkseeun.moca_android.ui.ranking

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetRankingResponse
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.detail.DetailActivity
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_ranking.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RankingActivity : AppCompatActivity(), View.OnClickListener {
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var getRankingResponse: Call<GetRankingResponse>
    private lateinit var rankingViewAdapter : RankingViewAdapter

    override fun onClick(v: View?) {
        val nextIntent = Intent(this, DetailActivity::class.java)
        nextIntent.putExtra("cafe_id", rankingViewAdapter.dataList[ranking_cafe_rv.getChildAdapterPosition(v!!)].cafe_id)
        startActivity(nextIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        ranking_back_iv.setOnClickListener { finish() }

        communicate()
    }
    private fun communicate() {
        getRankingResponse = networkService.getRanking(User.token, -1)
        getRankingResponse.enqueue(object : Callback<GetRankingResponse>{
            override fun onFailure(call: Call<GetRankingResponse>?, t: Throwable?) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetRankingResponse>?, response: Response<GetRankingResponse>?) {
                if (response!!.body()!!.status == 200) {
                    var dataList = response.body()!!.data
                    rankingViewAdapter = RankingViewAdapter(applicationContext, dataList)
                    rankingViewAdapter.setOnItemClickListener(this@RankingActivity)
                    ranking_cafe_rv.adapter = rankingViewAdapter
                    ranking_cafe_rv.layoutManager= GridLayoutManager(applicationContext,2)
                } else {
                    toast(response.body()!!.status.toString() + ": " + response.body()!!.message)
                }
            }

        })
    }
}
