package com.example.parkseeun.moca_android

import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.parkseeun.moca_android.ui.main.CategoryRankData
import com.example.parkseeun.moca_android.ui.main.MypageTabAdapter
import com.example.parkseeun.moca_android.ui.main.ScrapCafeActivity
import com.example.parkseeun.moca_android.ui.main.coupon.CouponActivity
import com.example.parkseeun.moca_android.ui.plus.PlusActivity
import kotlinx.android.synthetic.main.activity_home2.*
import org.jetbrains.anko.startActivity

abstract class NavigationActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{


    lateinit var headerView : View
    val membershipList = ArrayList<String>()

    fun setHeader() {

        makeData()

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

        val coupon : LinearLayout = headerView.findViewById(R.id.ll_mypage_tab_coupon) as LinearLayout
        coupon.setOnClickListener {
            startActivity<CouponActivity>()
        }

        val membershipRv : RecyclerView = headerView.findViewById(R.id.rv_act_home_membership) as RecyclerView
        membershipRv.layoutManager = GridLayoutManager(this, 4)
        membershipRv.adapter = MypageTabAdapter(this, membershipList)

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

    private fun makeData() {
        for (i in 1..12) {
            membershipList.add("카페 $i")
        }

    }
}