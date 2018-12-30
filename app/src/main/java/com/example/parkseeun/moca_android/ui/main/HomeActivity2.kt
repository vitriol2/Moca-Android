package com.example.parkseeun.moca_android.ui.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.plus.PlusActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home2.*
import kotlinx.android.synthetic.main.app_bar_home2.*
import kotlinx.android.synthetic.main.mypage_tab.*
import org.jetbrains.anko.startActivity

class HomeActivity2 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val pickposts: ArrayList<String> = ArrayList()
    val conceptPosts: ArrayList<String> = ArrayList()
    val rankingPosts: ArrayList<CategoryRankData> = ArrayList()
    val plusPosts: ArrayList<String> = ArrayList()

    private lateinit var headerView : View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
        setSupportActionBar(toolbar)

        makeData()

        recyclerView()

        setHeader()

        val snapHelper= LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_act_home_picks)




        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }


    private fun setHeader() {

        headerView = nav_view.getHeaderView(0)

        val image : LinearLayout = headerView.findViewById(R.id.ll_mypage_tab_home) as LinearLayout
        image.setOnClickListener {
            Log.v("vvvvv", "vvvvv")
            startActivity<PlusActivity>()
        }

        val scrapCafe : ImageView = headerView.findViewById(R.id.iv_mypage_tab_scrapcafe_more) as ImageView
        scrapCafe.setOnClickListener {
            startActivity<ScrapCafeActivity>()
        }

        val membershipRv : RecyclerView = headerView.findViewById(R.id.rv_act_home_membership) as RecyclerView
        membershipRv.layoutManager = GridLayoutManager(this, 4)
        membershipRv.adapter = MypageTabAdapter(this, pickposts)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_activity2, menu)
        return true
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.


        drawer_layout.closeDrawer(GravityCompat.START)
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

        //
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
