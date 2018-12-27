package com.example.parkseeun.moca_android.ui.plus.plusDetail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R

class PlusDetailImageRecyclerViewAdapter(val context : Context, val dataList : ArrayList<String>) : RecyclerView.Adapter<PlusDetailImageRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlusDetailImageRecyclerViewAdapter.Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_plus_detail_picture, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: PlusDetailImageRecyclerViewAdapter.Holder, position: Int) {
        // 뷰 바인딩
        Glide.with(context).load(dataList[position]).into(holder.iv_plus_detail_picture)

        // 클릭 리스너
        holder.iv_plus_detail_picture.setOnClickListener {
            // 클릭하면 이미지 바꾸기

        }
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val iv_plus_detail_picture : ImageView = itemView.findViewById(R.id.iv_plus_detail_picture) as ImageView
    }
}