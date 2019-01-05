package com.example.parkseeun.moca_android.ui.category.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.parkseeun.moca_android.R

class OptionViewAdapter(val context: Context, val dataList: ArrayList<OptionData>) : RecyclerView.Adapter<OptionViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view: View = LayoutInflater.from(context).inflate(R.layout.rv_item_option, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.opt_tv.text = dataList[position].name
        // flag(지역값인지)에 따라 배경과 텍스트 색상 결정
        // 뷰 기본 설정이 아닌 경우로 세팅되어 있어서 else는 구현하지 않아도 괜찮
        if(dataList[position].isRegion) {
            holder.opt_tv.setBackgroundResource(R.drawable.square_region)
            holder.opt_tv.setTextColor(context.resources.getColor(R.color.point_pink))
        }
    }

    // View Holder
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val opt_tv = itemView.findViewById(R.id.option_item_tv) as TextView
    }
}