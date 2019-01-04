package com.example.parkseeun.moca_android.ui.communitySearch

import android.support.v4.app.*

class CommunitySearchPagerAdapter(fm:FragmentManager, val FragmentCount : Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when(position) {
            0 -> return ComSearAllFragment()
            1 -> return ComSearCafeFragment()
            2 -> return ComSearUserFragment()
            else -> return null
        }
    }

    override fun getCount(): Int = FragmentCount

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> return "전체"
            1 -> return "카페명"
            2 -> return "사용자"
            else -> return null
        }
    }
}