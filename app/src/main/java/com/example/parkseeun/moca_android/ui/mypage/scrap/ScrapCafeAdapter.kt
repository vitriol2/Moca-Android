package com.example.parkseeun.moca_android.ui.mypage.scrap

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.ScrapCafeData
import com.example.parkseeun.moca_android.ui.detail.DetailActivity

class ScrapCafeAdapter(val ctx : Context, val dataList : ArrayList<ScrapCafeData>) : RecyclerView.Adapter<ScrapCafeAdapter.Holder>(){

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_act_scrapcafe, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.item.setOnClickListener {
            val intent = Intent(ctx, DetailActivity::class.java)
            ctx.startActivity(intent)
        }
        Glide.with(ctx).load( if (dataList[position].cafe_img_url.size==0) "https://s3.ap-northeast-2.amazonaws.com/project-sopt/commonDefaultimage%403x.png" else dataList[position].cafe_img_url[0].cafe_img_url ).into(holder.cafeimage)
        holder.cafename.text = dataList[position].cafe_name
        holder.location.text = dataList[position].address_district_name
        holder.rating.numStars = dataList[position].cafe_rating_avg

    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val item : RelativeLayout = itemView.findViewById(R.id.rl_rv_act_scrapcafe_item) as RelativeLayout
        val cafeimage : ImageView = itemView.findViewById(R.id.iv_rv_act_scrapcafe_cafeimage) as ImageView
        val cafename : TextView = itemView.findViewById(R.id.tv_rv_act_scrapcafe_cafename) as TextView
        val location : TextView = itemView.findViewById(R.id.tv_rv_act_scrapcafe_location) as TextView
        val scrap : ImageButton = itemView.findViewById(R.id.ib_rv_act_scrapcafe_scrap) as ImageButton
        val rating : RatingBar = itemView.findViewById(R.id.rb_rv_act_scrapcafe_item) as RatingBar
    }
}