package com.example.parkseeun.moca_android.ui.community.feed.image

import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.util.ImageAdapter
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        image_back_ib.setOnClickListener{
            finish()
        }

        // 스크린 너비에 따른 이미지 길이 설정
        image_img_vp.layoutParams.height = getScreenWidth()

        image_index_tv.text = "1/10"

        image_img_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(position: Int) {
                image_index_tv.text = (position+1).toString()+"/10"
            }
        })
        image_img_vp.adapter = ImageAdapter(this, arrayOf("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4"))
    }
    private fun getScreenWidth(): Int {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }
}
