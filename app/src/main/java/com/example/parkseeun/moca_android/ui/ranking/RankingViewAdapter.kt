package com.example.parkseeun.moca_android.ui.ranking

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

class RankingViewAdapter(val context : Context, val dataList : ArrayList<RankingData>) : RecyclerView.Adapter<RankingViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewAdapter.Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_ranking, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: RankingViewAdapter.Holder, position: Int) {
        Glide.with(context).load(dataList[position].pic).into(holder.pic)
        holder.cafename.text = dataList[position].cafename
        holder.rank.text = dataList[position].rank.toString()
        holder.location.text = dataList[position].location
        holder.rating.rating = dataList[position].rating.toFloat()
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val pic : ImageView = itemView.findViewById(R.id.rank_item_pic_iv) as ImageView
        val rank : TextView = itemView.findViewById(R.id.rank_item_rank_tv) as TextView
        val cafename : TextView = itemView.findViewById(R.id.rank_item_cafename_tv) as TextView
        val location : TextView = itemView.findViewById(R.id.rank_item_location_tv) as TextView
        val rating : RatingBar = itemView.findViewById(R.id.rank_item_rate_rating) as RatingBar
    }
}