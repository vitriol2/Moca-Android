package com.example.parkseeun.moca_android.ui.mypage

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.parkseeun.moca_android.R

class MypageTabAdapter(val ctx : Context, val dataList : ArrayList<Boolean>) : RecyclerView.Adapter<MypageTabAdapter.Holder>(){
    override fun onCreateViewHolder(container: ViewGroup, p1: Int): Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.mypage_tab_mem_item, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.mem.setImageResource(if(dataList[position]) R.drawable.mypage_stamp_pink
        else R.drawable.mypage_stamp_gray)
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var mem : ImageView = itemView.findViewById(R.id.iv_mypage_tab_mem_item) as ImageView
    }
}