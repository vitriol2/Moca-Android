package com.example.parkseeun.moca_android.ui.community.feed

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.parkseeun.moca_android.R

class FeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        addFragment(SocialFragment())
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
