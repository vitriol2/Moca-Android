package com.example.parkseeun.moca_android.ui.main.search

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class SearchPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    var pos: Int = 0
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> { return SearchAllFragment();pos=0 }
            1 -> { return SearchCafeFragment();pos=1 }
            else -> { return SearchLocationFragment();pos=2 }
        }
    }

    override fun getCount() = 3

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "전체"
            1 -> return "카페명"
            2 -> return "위치"
        }
        return null
    }
}