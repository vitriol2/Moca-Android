package com.example.parkseeun.moca_android.ui.community.feed.image

import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.util.ImageAdapter
import com.example.parkseeun.moca_android.util.PhotoViewAdapter
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        image_back_ib.setOnClickListener{
            finish()
        }
        val imgs : Array<String?> = intent.getStringArrayExtra("imgs")
        // 스크린 너비에 따른 이미지 길이 설정
        image_img_vp.layoutParams.height = getScreenWidth()

        image_index_tv.text = "1/${imgs.size}"

        image_img_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(position: Int) {
                image_index_tv.text = "${(position+1)}/${imgs.size}"
            }
        })
        image_img_vp.adapter = PhotoViewAdapter(this, imgs)

        // 몇 번째 페이지에서 눌렀는지 반영
        intent.getIntExtra("page", -1).let{
            if(it != -1){
                image_index_tv.text = "${it+1}/${imgs.size}"
                image_img_vp.currentItem = it
            }
        }
    }
    private fun getScreenWidth(): Int {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }
}
