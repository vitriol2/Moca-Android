package com.example.parkseeun.moca_android.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetMocaPicksListData
import com.example.parkseeun.moca_android.ui.mocapicks.MocaPicksListData
import kotlinx.android.synthetic.main.category_picks_rv_item.view.*

class CategoryPickAdapter(val ctx : Context, val dataList: ArrayList<GetMocaPicksListData>) : RecyclerView.Adapter<CategoryPickAdapter.Holder>(){
    override fun onCreateViewHolder(container: ViewGroup, p1: Int): CategoryPickAdapter.Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.category_picks_rv_item, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: CategoryPickAdapter.Holder, position: Int) {
        Glide.with(ctx).load(dataList[position].evaluated_cafe_img_url).into(holder.cafeImage)

        holder.cafename.text = dataList[position].cafe_name
        holder.location.text = dataList[position].address_district_name
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val cafeImage : ImageView = itemView.findViewById(R.id.iv_cat_pic_rv_item_image) as ImageView
        val cafename : TextView = itemView.findViewById(R.id.tv_cat_pic_rv_item_cafe_name) as TextView
        val location : TextView = itemView.findViewById(R.id.tv_cat_pic_rv_item_cafe_location) as TextView
    }
}