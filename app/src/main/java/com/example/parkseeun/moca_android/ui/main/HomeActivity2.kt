package com.example.parkseeun.moca_android.ui.main

import android.content.Intent
import com.example.parkseeun.moca_android.ui.ranking.RankingActivity
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.MenuItem
import android.view.View
import com.example.parkseeun.moca_android.util.NavigationActivity
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.mocapicks.MocaPicksListActivity
import com.example.parkseeun.moca_android.ui.plus.PlusActivity
import kotlinx.android.synthetic.main.activity_home2.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home2.*

class HomeActivity2 : NavigationActivity(), View.OnClickListener{
    private val pickposts: ArrayList<String> = ArrayList()
    private val conceptPosts: ArrayList<String> = ArrayList()
    val rankingPosts: ArrayList<CategoryRankData> = ArrayList()
    val plusPosts: ArrayList<String> = ArrayList()

    override fun onClick(v: View?) {
        when(v){
            home_picks_tv -> {
                startActivity(Intent(this, MocaPicksListActivity::class.java))
            }
            home_concept_tv -> {
//                to category
            }
            home_ranking_tv -> {
                startActivity(Intent(this, RankingActivity::class.java))
            }
            home_plus_tv -> {
                startActivity(Intent(this, PlusActivity::class.java))
            }
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (p0.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }

        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)

        home_picks_tv.setOnClickListener(this)
        home_concept_tv.setOnClickListener(this)
        home_ranking_tv.setOnClickListener(this)
        home_plus_tv.setOnClickListener(this)

        makeData()

        recyclerView()

        setHeader(nav_view)

        val snapHelper= LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_act_home_picks)




        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
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

        //
    }

    private fun makeData() {
        for (i in 1..12) {
            pickposts.add("카페 $i")
            conceptPosts.add("컨셉 $i")

        }
        for (i in 1..3) {
            rankingPosts.add(CategoryRankData("cafe $i", "location $i"))
            plusPosts.add("image $i")

        }
    }
}
