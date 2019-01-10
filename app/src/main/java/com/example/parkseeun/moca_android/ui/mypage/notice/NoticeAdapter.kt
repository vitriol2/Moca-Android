package com.example.parkseeun.moca_android.ui.mypage.notice

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.parkseeun.moca_android.R

class NoticeAdapter(val ctx : Context, val dataList : ArrayList<String>) : RecyclerView.Adapter<NoticeAdapter.Holder>(){

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_act_notice_item, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = dataList[position]
    }

    class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.tv_rv_act_notice_name) as TextView
    }

}