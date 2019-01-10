package com.example.parkseeun.moca_android.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetRankingResponseData
import kotlinx.android.synthetic.main.category_picks_rv_item.view.*

class CategoryRankingAdapter(val ctx : Context, val dataList: ArrayList<GetRankingResponseData>) : RecyclerView.Adapter<CategoryRankingAdapter.Holder>(){
    override fun onCreateViewHolder(container: ViewGroup, p1: Int): CategoryRankingAdapter.Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.category_ranking_rv_item, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: CategoryRankingAdapter.Holder, position: Int) {
        val rank = position+1
        holder.rank.text = rank.toString()
        Glide.with(ctx).load(dataList[position].cafe_menu_img_url).into(holder.cafeImage)

        holder.cafeName.text = dataList[position].cafe_name
        holder.location.text = dataList[position].address_district_name

        holder.rating.rating = dataList[position].cafe_rating_avg.toFloat()

    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val cafeImage : ImageView = itemView.findViewById(R.id.iv_cat_ran_rv_item_cafeimage) as ImageView
        val cafeName : TextView = itemView.findViewById(R.id.tv_cat_ran_rv_item_cafename) as TextView
        val location : TextView = itemView.findViewById(R.id.tv_cat_ran_rv_item_location) as TextView
        val rank : TextView = itemView.findViewById(R.id.tv_cat_ran_rv_item_rank) as TextView
        val rating : RatingBar = itemView.findViewById(R.id.rb_cat_ran_rv_item_rating) as RatingBar

    }
}