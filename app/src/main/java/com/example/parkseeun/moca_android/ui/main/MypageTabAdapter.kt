package com.example.parkseeun.moca_android.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.parkseeun.moca_android.R

class MypageTabAdapter(val ctx : Context, val dataList : ArrayList<String>) : RecyclerView.Adapter<MypageTabAdapter.Holder>(){
    override fun onCreateViewHolder(container: ViewGroup, p1: Int): MypageTabAdapter.Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.mypage_tab_mem_item, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MypageTabAdapter.Holder, position: Int) {
        holder.num.text = dataList[position]
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val num : TextView = itemView.findViewById(R.id.iv_mypage_tab_mem_item) as TextView
    }
}