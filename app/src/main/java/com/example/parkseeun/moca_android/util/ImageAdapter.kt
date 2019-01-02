package com.example.parkseeun.moca_android.util

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageAdapter(private val mContext : Context, private var mImgItem : Array<String>) : PagerAdapter() {
    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0==p1
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var img = ImageView(mContext)
        img.scaleType = ImageView.ScaleType.CENTER_CROP
        Glide.with(mContext).load(mImgItem[position]).into(img)
        container.addView(img, 0)
        return img
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }

    override fun getCount(): Int = mImgItem.size
}

