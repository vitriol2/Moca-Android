package com.example.parkseeun.moca_android.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.HomeHotplaceData

class HomeHotplaceAdapter(val ctx : Context, val dataList: ArrayList<HomeHotplaceData>) : RecyclerView.Adapter<HomeHotplaceAdapter.Holder>(){
    override fun onCreateViewHolder(container: ViewGroup, p1: Int): HomeHotplaceAdapter.Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_act_home2_hotplace_item, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: HomeHotplaceAdapter.Holder, position: Int) {
        val requestOptions = RequestOptions()

        Glide.with(ctx)
            .setDefaultRequestOptions(requestOptions)
            .load(dataList[position].hot_place_img)
            .into(holder.image)
        holder.cafename.text = dataList[position].hot_place_name
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.iv_rv_act_home2_hotplace) as ImageView
        val cafename : TextView = itemView.findViewById(R.id.tv_rv_act_home2_hotplace) as TextView
    }
}