package com.example.parkseeun.moca_android.ui.community.review_write

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_community_writereview.*
import org.jetbrains.anko.startActivity
import android.widget.RatingBar
import android.widget.TextView
import com.example.parkseeun.moca_android.ui.community.review_write.ReviewSearchLocationActivity


class WriteReviewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_writereview)
        SetOnClickListener()
        ratingBar()
    }

    fun ratingBar(){
        val ratingBarCustom = findViewById<View>(R.id.ratingBarCustom) as RatingBar
        ratingBarCustom.onRatingBarChangeListener = object : RatingBar.OnRatingBarChangeListener {
            var ratingBarCustomInfo = findViewById<View>(R.id.ratingBarCustomInfo) as TextView
            override fun onRatingChanged(ratingBar: RatingBar, ratingValue: Float, fromUser: Boolean) {
                val currentProgress = ratingBarCustom.progress
                ratingBarCustomInfo.text = "Current start number is $ratingValue, Progress number is $currentProgress"
            }
        }

    }

    fun SetOnClickListener() {
        txt_addreview_cafeaddress.setOnClickListener {
            startActivity<ReviewSearchLocationActivity>()
        }
    }


}