package com.example.parkseeun.moca_android.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.rv_scrapcafe_item.view.*

class ScrapCafeAdapter(val ctx : Context, val dataList : ArrayList<ScrapCafeData>) : RecyclerView.Adapter<ScrapCafeAdapter.Holder>(){

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): ScrapCafeAdapter.Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_scrapcafe_item, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ScrapCafeAdapter.Holder, position: Int) {
        holder.name.text = dataList[position].cafename
        holder.location.text = dataList[position].location

    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.tv_rv_zzim_item_name) as TextView
        val location : TextView = itemView.findViewById(R.id.tv_rv_zzim_item_location) as TextView
    }
}