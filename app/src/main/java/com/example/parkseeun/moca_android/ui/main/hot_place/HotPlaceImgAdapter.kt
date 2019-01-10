package com.example.parkseeun.moca_android.ui.main.hot_place

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R

class HotPlaceImgAdapter(val context : Context, val dataList : ArrayList<String>) : RecyclerView.Adapter<HotPlaceImgAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_hot_place_img, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 뷰 바인딩
        holder.rv_hot_place_img.setOnClickListener {

        }

        Glide.with(context).load(dataList[position]).into(holder.iv_hot_place_img) // 이미지 넣기

    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val rv_hot_place_img : RelativeLayout = itemView.findViewById(R.id.rv_hot_place_img) as RelativeLayout
        val iv_hot_place_isPick : ImageView = itemView.findViewById(R.id.iv_hot_place_isPick) as ImageView
        val iv_hot_place_img : ImageView = itemView.findViewById(R.id.iv_hot_place_img) as ImageView
    }
}