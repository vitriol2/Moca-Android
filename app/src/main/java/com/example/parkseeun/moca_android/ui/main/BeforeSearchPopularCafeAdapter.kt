package com.example.parkseeun.moca_android.ui.main

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.detail.DetailActivity

class BeforeSearchPopularCafeAdapter(val context : Context, val dataList : ArrayList<PopularCafeData>) : RecyclerView.Adapter<BeforeSearchPopularCafeAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeforeSearchPopularCafeAdapter.Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_search_popular_cafe, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: BeforeSearchPopularCafeAdapter.Holder, position: Int) {
        holder.iv_popular_cafe.clipToOutline = true

        // 뷰 바인딩
        Glide.with(context).load(dataList[position].cafe_img).into(holder.iv_popular_cafe)
        holder.tv_popular_cafe_name.text = dataList[position].cafe_name
        holder.tv_popular_cafe_scrap_num.text = dataList[position].scrap_number.toString()

        // 클릭
        holder.rv_item_search_popular_cafe_relative.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)

            context.startActivity(intent)
        }

        // 스크랩 버튼
        holder.btn_popular_cafe_scrap.setOnClickListener {

        }
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val rv_item_search_popular_cafe_relative : RelativeLayout = itemView.findViewById(R.id.rv_item_search_popular_cafe_relative) as RelativeLayout
        val iv_popular_cafe : ImageView = itemView.findViewById(R.id.iv_popular_cafe) as ImageView
        val tv_popular_cafe_name : TextView = itemView.findViewById(R.id.tv_popular_cafe_name) as TextView
        val btn_popular_cafe_scrap : ImageButton = itemView.findViewById(R.id.btn_popular_cafe_scrap) as ImageButton
        val tv_popular_cafe_scrap_num : TextView = itemView.findViewById(R.id.tv_popular_cafe_scrap_num) as TextView
    }
}