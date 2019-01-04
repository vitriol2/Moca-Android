package com.example.parkseeun.moca_android.ui.detail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.category_picks_rv_item.view.*

class DetailMenuAdapter(val ctx : Context, val dataList: ArrayList<DetailMenuData>) : RecyclerView.Adapter<DetailMenuAdapter.Holder>() {
    override fun onCreateViewHolder(container: ViewGroup, p1: Int): DetailMenuAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_act_detail_menu_item, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: DetailMenuAdapter.Holder, position: Int) {
        holder.menuName.text = dataList[position].name
        holder.menuPrice.text = dataList[position].price
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuName: TextView = itemView.findViewById(R.id.tv_rv_act_datail_menu_name) as TextView
        val menuPrice: TextView = itemView.findViewById(R.id.tv_rv_act_datail_menu_price) as TextView
    }
}