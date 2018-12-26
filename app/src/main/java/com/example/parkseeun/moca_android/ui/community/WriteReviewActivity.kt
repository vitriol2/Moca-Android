package com.example.parkseeun.moca_android.ui.community

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_community_writereview.*
import org.jetbrains.anko.startActivity

class WriteReviewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_writereview)
        SetOnClickListener()
    }

    fun SetOnClickListener() {
        txt_addreview_cafeaddress.setOnClickListener {
            startActivity<ReviewSearchLocationActivity>()
        }
    }
}