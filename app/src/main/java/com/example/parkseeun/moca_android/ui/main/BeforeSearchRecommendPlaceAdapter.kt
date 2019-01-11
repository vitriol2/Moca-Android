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
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetMocaRecommendHotPlaceData
import com.example.parkseeun.moca_android.ui.main.hot_place.HotPlaceActivity

class BeforeSearchRecommendPlaceAdapter(val context : Context, val dataList : ArrayList<GetMocaRecommendHotPlaceData>) : RecyclerView.Adapter<BeforeSearchRecommendPlaceAdapter.Holder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeforeSearchRecommendPlaceAdapter.Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_search_recommend_place, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: BeforeSearchRecommendPlaceAdapter.Holder, position: Int) {
        holder.iv_search_recommend_place_image.clipToOutline = true
        Glide.with(context).load(dataList[position].hot_place_img).into(holder.iv_search_recommend_place_image)
        holder.tv_search_recommend_place_tag.text = "#${dataList[position].hot_place_name}"

        // 클릭
        holder.rv_item_search_recommend_place_relative.setOnClickListener {
            val intent = Intent(context, HotPlaceActivity::class.java)
            intent.putExtra("hot_place_id", dataList[position].hot_place_id)
            intent.putExtra("hot_place_name", dataList[position].hot_place_name)

            context.startActivity(intent)
        }
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val rv_item_search_recommend_place_relative : RelativeLayout = itemView.findViewById(R.id.rv_item_search_recommend_place_relative) as RelativeLayout
        val iv_search_recommend_place_image : ImageView = itemView.findViewById(R.id.iv_search_recommend_place_image) as ImageView
        val tv_search_recommend_place_tag : TextView = itemView.findViewById(R.id.tv_search_recommend_place_tag) as TextView
    }
}