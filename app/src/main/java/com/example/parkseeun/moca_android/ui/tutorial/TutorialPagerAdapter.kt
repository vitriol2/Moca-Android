package com.example.parkseeun.moca_android.ui.tutorial

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TutorialPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> Tutorial1Fragment()
            1 -> Tutorial2Fragment()
            2 -> Tutorial3Fragment()
            else -> Tutorial4Fragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "소개"
            1 -> return "물품"
            2 -> return "앨범"
            3 -> return "후기"
        }
        return null
    }
}