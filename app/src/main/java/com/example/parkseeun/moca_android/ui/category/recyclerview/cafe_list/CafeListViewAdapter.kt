package com.example.parkseeun.moca_android.ui.category.recyclerview.cafe_list

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R

import com.example.parkseeun.moca_android.ui.detail.DetailActivity
import com.example.parkseeun.moca_android.model.get.GetCafeListResponseData

class CafeListViewAdapter(val context: Context, val dataList: ArrayList<GetCafeListResponseData>) : RecyclerView.Adapter<CafeListViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view: View = LayoutInflater.from(context).inflate(R.layout.rv_item_cafe, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(context).load(dataList[position].cafe_menu_img_url).into(holder.img)
        holder.name.text = dataList[position].cafe_name
        holder.location.text = dataList[position].cafe_address_detail
        holder.rating.rating = dataList[position].cafe_rating_avg.toFloat()

        holder.optionItems.adapter = OptionItemViewAdapter(context, arrayListOf(dataList[position].cafe_main_menu_name, dataList[position].cafe_concept_name))
        holder.optionItems.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        holder.constraint_cafe_list.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("cafe_id", dataList[position].cafe_id)
            context.startActivity(intent)
        }
    }

    // View Holder
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val constraint_cafe_list = itemView.findViewById(R.id.constraint_cafe_list) as ConstraintLayout
        val img = itemView.findViewById(R.id.cafe_item_img_iv) as ImageView
        val name = itemView.findViewById(R.id.cafe_item_name_tv) as TextView
        val location = itemView.findViewById(R.id.cafe_item_location_tv) as TextView
        val rating = itemView.findViewById(R.id.cafe_item_rating) as RatingBar
        val optionItems = itemView.findViewById(R.id.cafe_item_rv) as RecyclerView
    }
}