package com.example.parkseeun.moca_android.util

import android.content.Intent
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
import android.widget.RelativeLayout
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.category.CategoryActivity
import com.example.parkseeun.moca_android.ui.community.feed.FeedActivity
import com.example.parkseeun.moca_android.ui.main.*
import com.example.parkseeun.moca_android.ui.location.LocationSearchActivity
import com.example.parkseeun.moca_android.ui.main.EditProfileActivity
import com.example.parkseeun.moca_android.ui.main.HomeActivity2
import com.example.parkseeun.moca_android.ui.main.MypageTabAdapter
import com.example.parkseeun.moca_android.ui.main.notice.NoticeActivity
import com.example.parkseeun.moca_android.ui.main.coupon.CouponActivity
import com.example.parkseeun.moca_android.ui.plus.PlusActivity
import kotlinx.android.synthetic.main.activity_home2.*
import kotlinx.android.synthetic.main.mypage_tab.*
import kotlinx.android.synthetic.main.mypage_tab.view.*
import org.jetbrains.anko.startActivity

abstract class NavigationActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    private val TAG = "NavigationActivity"

    private var mem_num : Int = 5
    lateinit var headerView : View
    val membershipList = ArrayList<Boolean>()

    fun setHeader(view_navi : NavigationView) {

        val intent : Intent
        makeData()

        headerView = view_navi.getHeaderView(0)

        val notice : RelativeLayout = headerView.findViewById(R.id.iv_mypage_tab_notice) as RelativeLayout
        notice.setOnClickListener {
            startActivity<NoticeActivity>()
        }

        val setting : ImageView = headerView.findViewById(R.id.iv_mypage_tab_setting) as ImageView
        setting.setOnClickListener {

            startActivity<EditProfileActivity>()
        }

        val home : ImageView = headerView.findViewById(R.id.iv_mypage_tab_home) as ImageView
        home.setOnClickListener {

            val intent = Intent(this, HomeActivity2::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        val category : ImageView = headerView.findViewById(R.id.iv_mypage_tab_category) as ImageView
        category.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        val location : ImageView = headerView.findViewById(R.id.iv_mypage_tab_location) as ImageView
        location.setOnClickListener {
            val intent = Intent(this, LocationSearchActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        val community : ImageView = headerView.findViewById(R.id.iv_mypage_tab_community) as ImageView
        community.setOnClickListener {
            val intent = Intent(this, FeedActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }


        val scrapCafe : ImageView = headerView.findViewById(R.id.iv_mypage_tab_scrapcafe_more) as ImageView
        scrapCafe.setOnClickListener {
            startActivity<ScrapCafeActivity>()
        }

        val coupon : LinearLayout = headerView.findViewById(R.id.ll_mypage_tab_coupon) as LinearLayout
        coupon.setOnClickListener {
            startActivity<CouponActivity>()
        }



        val membershipRv : RecyclerView = headerView.findViewById(R.id.rv_act_home_membership)
        membershipRv.layoutManager = GridLayoutManager(this, 4)
        membershipRv.adapter = MypageTabAdapter(this, membershipList)


        // 멤버십 개수
//        headerView.tv_act_home_membership_num!!.text = "$mem_num/12"
        headerView.iv_act_home_membership.setOnClickListener {

        }
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
//        menuInflater.inflate(R.menu.home_activity2, menu)
        return true
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.


        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun makeData() {
        for (i in 1..mem_num) {
            membershipList.add(true)
        }
        for (i in mem_num+1..12)
            membershipList.add(false)
    }
}