package com.example.parkseeun.moca_android.ui.main

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.SnapHelper
import android.view.Menu
import android.view.MenuItem
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.CafeListData
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    val drawerToggle by lazy {
        ActionBarDrawerToggle(this, dl_act_home, toolbar, R.string.open, R.string.close)
    }

    val pickposts: ArrayList<String> = ArrayList()
    val conceptPosts: ArrayList<String> = ArrayList()
    val rankingPosts: ArrayList<CategoryRankData> = ArrayList()
    val plusPosts: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setNavigation()

        makeData()

        recyclerView()

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_act_home_picks)
    }

    // 왼쪽상단 마이페이지탭 열기
    private fun setNavigation() {
        setSupportActionBar(toolbar)
        dl_act_home.addDrawerListener(drawerToggle)
    }

    override fun onBackPressed() {
        if (dl_act_home.isDrawerOpen(GravityCompat.START)) {
            dl_act_home.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.mypage_menu, menu)
        return true
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
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

        dl_act_home.closeDrawer(GravityCompat.START)
        return true
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






