package com.example.parkseeun.moca_android.ui.community.review_detail


import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.util.ImageAdapter
import kotlinx.android.synthetic.main.activity_review_detail.*

class ReviewDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_detail)

        // 스크린 너비에 따른 이미지 길이 설정
        review_detail_pic_vp.layoutParams.height = getScreenWidth()
        review_detail_space_view.layoutParams.height = getScreenWidth() - dpToPx(13.toFloat()).toInt()

        // image viewpager setting
        review_detail_page_tv.text = "1/10"
        review_detail_pic_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(position: Int) {
                review_detail_page_tv.text = (position+1).toString()+"/10"
            }
        })
        review_detail_pic_vp.adapter = ImageAdapter(this, arrayOf("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4"), true)

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
