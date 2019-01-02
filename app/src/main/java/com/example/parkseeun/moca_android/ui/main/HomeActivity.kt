package com.example.parkseeun.moca_android.ui.main

import android.content.Intent
import android.view.View
import com.example.parkseeun.moca_android.ui.plus.PlusActivity
import com.example.parkseeun.moca_android.ui.ranking.RankingActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.SnapHelper
import android.view.MenuItem
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.mocapicks.MocaPicksListActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{
    private lateinit var mToggle: ActionBarDrawerToggle

    val pickposts: ArrayList<String> = ArrayList()
    val conceptPosts: ArrayList<String> = ArrayList()
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

        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        home_picks_tv.setOnClickListener(this)
        home_concept_tv.setOnClickListener(this)
        home_ranking_tv.setOnClickListener(this)
        home_plus_tv.setOnClickListener(this)

        setNavigation()

        makeData()

        recyclerView()

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_act_home_picks)
    }

    private fun setNavigation() {
        setSupportActionBar(toolbar)

        mToggle = ActionBarDrawerToggle(this, dl_act_home, toolbar, R.string.open, R.string.close)
        dl_act_home.addDrawerListener(mToggle)
        mToggle.syncState()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onBackPressed() {
        if (dl_act_home.isDrawerOpen(GravityCompat.START)) {
            dl_act_home.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(mToggle.onOptionsItemSelected(item)){
            return true
        }else {
            return super.onOptionsItemSelected(item)
        }

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
            conceptPosts.add("컨셉 $i")

        }
        for (i in 1..3) {
            rankingPosts.add(CategoryRankData("cafe $i", "location $i"))
            plusPosts.add("image $i")

        }
    }
}






