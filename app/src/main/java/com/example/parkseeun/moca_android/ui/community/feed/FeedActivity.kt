package com.example.parkseeun.moca_android.ui.community.feed

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v){
            feed_title_tv, feed_title_iv -> {
                // 아이콘 숨기기
                feed_menu_iv.visibility = View.GONE
                feed_search_iv.visibility = View.GONE
                feed_dm_iv.visibility = View.GONE
                // 취소 아이콘 표시
                feed_cancel_iv.visibility = View.VISIBLE
                // 드롭다운 메뉴 표시
                feed_black_frame.visibility = View.VISIBLE
                feed_dropdown_linear.visibility = View.VISIBLE
            }
            feed_cancel_iv -> {
                // 피드에 따른 아이콘 표시
                if(feed_title_tv.text == "내 피드"){
                    feed_menu_iv.visibility = View.VISIBLE
                    feed_search_iv.visibility = View.GONE
                    feed_dm_iv.visibility = View.VISIBLE
                    feed_cancel_iv.visibility = View.GONE
                }else{
                    feed_menu_iv.visibility = View.VISIBLE
                    feed_search_iv.visibility = View.VISIBLE
                    feed_dm_iv.visibility = View.GONE
                    feed_cancel_iv.visibility = View.GONE
                }
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
        setContentView(R.layout.activity_feed)
        addFragment(SocialFragment())

        feed_title_tv.setOnClickListener(this)
        feed_title_iv.setOnClickListener(this)
        feed_my_const.setOnClickListener(this)
        feed_social_const.setOnClickListener(this)
        feed_cancel_iv.setOnClickListener(this)
    }

    //Fragment 붙이는 함수
    fun addFragment(fragment: Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.feed_fragment_frame,fragment)
        transaction.commit()
    }

    //Fragment 교체
    fun replaceFragment(fragment: Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.feed_fragment_frame,fragment)
        transaction.commit()
    }
}
