package com.example.parkseeun.moca_android.ui.main

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.detail.DetailActivity
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.find

class HotPlaceAdapter(val context : Context, val dataList : ArrayList<HotPlaceData>) : RecyclerView.Adapter<HotPlaceAdapter.Holder>() {

    lateinit var hotPlaceImgAdapter: HotPlaceImgAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_hot_place, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 뷰 바인딩
        Glide.with(context).load(dataList[position].cafe_img).into(holder.civ_hot_place)
        holder.tv_hot_place_name.text = dataList[position].cafe_name
        holder.tv_hot_place_explain.text = dataList[position].cafe_explain
        holder.rating_rv_item_hot_place.numStars = dataList[position].rate

        // RecyclerView 설정
        var imgList = dataList[position].imgList

        hotPlaceImgAdapter = HotPlaceImgAdapter(context, imgList)
        holder.rv_hot_place_img.adapter = hotPlaceImgAdapter
        holder.rv_hot_place_img.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // 클릭 리스너
        holder.relative_rv_item_hot_place.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)

            context.startActivity(intent)
        }
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val relative_rv_item_hot_place : RelativeLayout = itemView.findViewById(R.id.relative_rv_item_hot_place) as RelativeLayout
        val civ_hot_place : CircleImageView = itemView.findViewById(R.id.civ_hot_place) as CircleImageView
        val tv_hot_place_name : TextView = itemView.findViewById(R.id.tv_hot_place_name) as TextView
        val tv_hot_place_explain : TextView = itemView.findViewById(R.id.tv_hot_place_explain) as TextView
        val rating_rv_item_hot_place : RatingBar = itemView.findViewById(R.id.rating_rv_item_hot_place) as RatingBar
        val rv_hot_place_img : RecyclerView = itemView.findViewById(R.id.rv_hot_place_img) as RecyclerView
    }
}