package com.example.parkseeun.moca_android.ui.community.feed

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.util.Log
import android.view.View
import com.example.parkseeun.moca_android.NavigationActivity
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_feed2.*
import kotlinx.android.synthetic.main.app_bar_community.*

class FeedActivity : NavigationActivity(), View.OnClickListener{
    private val TAG = "FeedActivity onCreate"

    override fun onClick(v: View?) {
        when(v){
            feed_title_tv, feed_title_iv -> {
                // 아이콘 숨기기
                feed_search_iv.visibility = View.GONE
                feed_dm_iv.visibility = View.GONE
                // 취소 아이콘 표시
                feed_menu_iv.visibility = View.GONE
                feed_cancel_iv.visibility = View.VISIBLE
                // 드롭다운 메뉴 표시
                feed_black_frame.visibility = View.VISIBLE
                feed_dropdown_linear.visibility = View.VISIBLE
            }
            feed_menu_iv -> {
                drawer_layout_feed.openDrawer(nav_view_feed)
            }
            feed_cancel_iv -> {
                // 피드에 따른 아이콘 표시
                if(feed_title_tv.text == "내 피드"){
                    feed_search_iv.visibility = View.GONE
                    feed_dm_iv.visibility = View.VISIBLE
                }else{
                    feed_search_iv.visibility = View.VISIBLE
                    feed_dm_iv.visibility = View.GONE
                }
                feed_cancel_iv.visibility = View.GONE
                feed_menu_iv.visibility = View.VISIBLE
                // 드롭다운 메뉴 숨기기
                feed_black_frame.visibility = View.GONE
                feed_dropdown_linear.visibility = View.GONE
            }
            feed_my_const -> {
                feed_my_iv.visibility = View.VISIBLE
                feed_social_iv.visibility = View.GONE
                feed_title_tv.text = "내 피드"
                replaceFragment(MyFragment())
                feed_cancel_iv.performClick()
            }
            feed_social_const -> {
                feed_my_iv.visibility = View.GONE
                feed_social_iv.visibility = View.VISIBLE
                feed_title_tv.text = "소셜 피드"
                replaceFragment(SocialFragment())
                feed_cancel_iv.performClick()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed2)
        addFragment(SocialFragment())

//        setSupportActionBar(toolbar_feed)

        setHeader(nav_view_feed)

//        val toggle = ActionBarDrawerToggle(
//            this, drawer_layout_feed, toolbar_feed, R.string.navigation_drawer_open, R.string.navigation_drawer_close
//

//        drawer_layout_feed.addDrawerListener(toggle)
//        toggle.syncState()

        nav_view_feed.setNavigationItemSelectedListener(this)

        Log.v(TAG, "onCreate")

        feed_title_tv.setOnClickListener(this)
        feed_title_iv.setOnClickListener(this)
        feed_my_const.setOnClickListener(this)
        feed_social_const.setOnClickListener(this)
        feed_menu_iv.setOnClickListener(this)
        feed_cancel_iv.setOnClickListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout_feed.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_feed.closeDrawer(GravityCompat.START)
        } else {
            finishAfterTransition()
        }
    }

    //Fragment 붙이는 함수
    fun addFragment(fragment: Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.feed_fragment_frame,fragment)
        transaction.commit()
        Log.v(TAG, "socialFragment added")
    }

    //Fragment 교체
    fun replaceFragment(fragment: Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.feed_fragment_frame,fragment)
        transaction.commit()
    }
}
