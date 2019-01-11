package com.example.parkseeun.moca_android.ui.tutorial

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.tutorial.TutorialPagerAdapter
import kotlinx.android.synthetic.main.activity_tutorial.*

class TutorialActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var pagerManager: TutorialPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
        pagerManager = TutorialPagerAdapter(supportFragmentManager)
        viewPager = tutorial_vp

        viewPager.adapter = pagerManager

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                when(p0){
                    0 -> {
                        tutorial_circle1.setImageResource(R.drawable.tutorial_circle_true)
                        tutorial_circle2.setImageResource(R.drawable.tutorial_circle_false)
                        tutorial_circle3.setImageResource(R.drawable.tutorial_circle_false)
                        tutorial_circle4.setImageResource(R.drawable.tutorial_circle_false)
                    }
                    1 -> {
                        tutorial_circle1.setImageResource(R.drawable.tutorial_circle_false)
                        tutorial_circle2.setImageResource(R.drawable.tutorial_circle_true)
                        tutorial_circle3.setImageResource(R.drawable.tutorial_circle_false)
                        tutorial_circle4.setImageResource(R.drawable.tutorial_circle_false)
                    }
                    2 -> {
                        tutorial_circle1.setImageResource(R.drawable.tutorial_circle_false)
                        tutorial_circle2.setImageResource(R.drawable.tutorial_circle_false)
                        tutorial_circle3.setImageResource(R.drawable.tutorial_circle_true)
                        tutorial_circle4.setImageResource(R.drawable.tutorial_circle_false)
                    }
                    3 -> {
                        tutorial_circle1.setImageResource(R.drawable.tutorial_circle_false)
                        tutorial_circle2.setImageResource(R.drawable.tutorial_circle_false)
                        tutorial_circle3.setImageResource(R.drawable.tutorial_circle_false)
                        tutorial_circle4.setImageResource(R.drawable.tutorial_circle_true)
                    }
                }
            }

        })
    }
}
