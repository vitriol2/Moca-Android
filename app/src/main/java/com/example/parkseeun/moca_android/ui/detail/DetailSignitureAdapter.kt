package com.example.parkseeun.moca_android.ui.detail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.category_picks_rv_item.view.*

class DetailSignitureAdapter(val ctx : Context, val dataList: ArrayList<String>) : RecyclerView.Adapter<DetailSignitureAdapter.Holder>(){
    override fun onCreateViewHolder(container: ViewGroup, p1: Int): DetailSignitureAdapter.Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_act_detail_signiture_item, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: DetailSignitureAdapter.Holder, position: Int) {
        holder.menuName.text = dataList[position]
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val menuName : TextView = itemView.findViewById(R.id.tv_rv_act_detail_sig_item_menuname) as TextView
    }
}