package com.example.parkseeun.moca_android.ui.main

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.HomeHotplaceData
import com.example.parkseeun.moca_android.ui.main.hot_place.HotPlaceActivity

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

        holder.cafename.text = "#" + dataList[position].hot_place_name

        // 클릭하면 액티비티 이동
        holder.relative_home_hot_place.setOnClickListener {
            val intent = Intent(ctx, HotPlaceActivity::class.java)
            intent.putExtra("hot_place_id", dataList[position].hot_place_id)
            intent.putExtra("hot_place_name", dataList[position].hot_place_name)

            ctx.startActivity(intent)
        }
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val relative_home_hot_place : RelativeLayout = itemView.findViewById(R.id.relative_home_hot_place) as RelativeLayout
        val image : ImageView = itemView.findViewById(R.id.iv_rv_act_home2_hotplace) as ImageView
        val cafename : TextView = itemView.findViewById(R.id.tv_rv_act_home2_hotplace) as TextView
    }
}