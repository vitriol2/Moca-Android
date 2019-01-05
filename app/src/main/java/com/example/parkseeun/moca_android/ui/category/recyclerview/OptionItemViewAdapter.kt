package com.example.parkseeun.moca_android.ui.category.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.parkseeun.moca_android.R

class OptionItemViewAdapter(val context: Context, val dataList: ArrayList<String>) : RecyclerView.Adapter<OptionItemViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view: View = LayoutInflater.from(context).inflate(R.layout.rv_item_item_option, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.opt_tv.text = dataList[position]
    }

    // View Holder
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val opt_tv = itemView.findViewById(R.id.option_item_item_tv) as TextView
    }
}