package com.example.parkseeun.moca_android.ui.community.review_detail


import android.content.Intent
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.community.feed.image.ImageActivity
import kotlinx.android.synthetic.main.activity_review_detail.*

class ReviewDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_detail)
        review_detail_pic_iv.setOnClickListener {
            startActivity(Intent(this, ImageActivity::class.java))
        }
        // 스크린 너비에 따른 이미지 길이 설정
        review_detail_pic_iv.layoutParams.height = getScreenWidth()
        review_detail_space_view.layoutParams.height = getScreenWidth() - dpToPx(13.toFloat()).toInt()
    }

    private fun dpToPx(dp:Float):Float{
        return (dp * this.resources.displayMetrics.density)
    }
    private fun getScreenWidth(): Int {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }
}
