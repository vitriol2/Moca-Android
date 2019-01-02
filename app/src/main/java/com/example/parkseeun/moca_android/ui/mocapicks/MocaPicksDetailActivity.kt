package com.example.parkseeun.moca_android.ui.mocapicks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.util.ImageAdapter
import kotlinx.android.synthetic.main.activity_moca_picks_detail.*

class MocaPicksDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moca_picks_detail)

        var urlList = arrayOf("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG","http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG")
        progressBar_mocaPicksDetail.max = urlList.size

        vp_mocaPicksDetail.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) { // 이미지 넘길 떄 하는 일은 여기서,,
                progressBar_mocaPicksDetail.progress = p0 + 1
            }
        })

        vp_mocaPicksDetail.adapter = ImageAdapter(this, urlList)


    }
}
