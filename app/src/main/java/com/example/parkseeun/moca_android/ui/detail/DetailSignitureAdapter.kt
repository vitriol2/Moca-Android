package com.example.parkseeun.moca_android.ui.detail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.SignitureData
import kotlinx.android.synthetic.main.category_picks_rv_item.view.*

class DetailSignitureAdapter(val ctx : Context, val dataList: ArrayList<SignitureData>) : RecyclerView.Adapter<DetailSignitureAdapter.Holder>(){
    override fun onCreateViewHolder(container: ViewGroup, p1: Int): DetailSignitureAdapter.Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_act_detail_signiture_item, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: DetailSignitureAdapter.Holder, position: Int) {
        holder.menuName.text = dataList[position].cafe_signiture_menu
        holder.menuPrice.text = dataList[position].cafe_signiture_price

        Glide.with(ctx).load(dataList[position].cafe_signiture_img).into(holder.menuImage)

    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val menuName : TextView = itemView.findViewById(R.id.tv_rv_act_detail_sig_item_menuname) as TextView
        val menuPrice : TextView = itemView.findViewById(R.id.tv_rv_act_detail_sig_item_price) as TextView
        val menuImage : ImageView = itemView.findViewById(R.id.iv_rv_act_detail_sig_item_image) as ImageView
    }
}