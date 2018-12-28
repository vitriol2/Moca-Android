package com.example.parkseeun.moca_android.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.SnapHelper
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.CafeListData
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    val pickposts : ArrayList<String> = ArrayList()
    val conceptPosts : ArrayList<String> = ArrayList()
    val rankingPosts : ArrayList<CategoryRankData> = ArrayList()
    val plusPosts : ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        makeData()

        recyclerView()

        val snapHelper : SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_act_home_picks)
    }

    private fun recyclerView() {
        //cafecloud pick
        rv_act_home_picks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_act_home_picks.adapter = CategoryPickAdapter(this, pickposts)

        //concept
        rv_act_home_concept.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_act_home_concept.adapter = CategoryConceptAdapter(this, conceptPosts)

        //ranking
        rv_act_home_ranking.layoutManager = LinearLayoutManager(this)
        rv_act_home_ranking.adapter = CategoryRankingAdapter(this, rankingPosts)

        //plus
        rv_act_home_plus.layoutManager = LinearLayoutManager(this)
        rv_act_home_plus.adapter = CategoryPlusAdapter(this, plusPosts)
    }

    private fun makeData() {
        for (i in 1..15) {
            pickposts.add("카페 $i")
            conceptPosts.add("컨셉 $i")d

        }
        for(i in 1..3) {
            rankingPosts.add(CategoryRankData("cafe $i", "location $i"))
            plusPosts.add("image $i")

        }
    }



}
