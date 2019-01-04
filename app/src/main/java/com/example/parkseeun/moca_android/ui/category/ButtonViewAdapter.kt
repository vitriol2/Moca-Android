package com.example.parkseeun.moca_android.ui.category

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.parkseeun.moca_android.R

class ButtonViewAdapter(val context: Context,val dataList: ArrayList<ButtonData>) : RecyclerView.Adapter<ButtonViewAdapter.Holder>() {
    private lateinit var onItemClick : View.OnClickListener

    fun setOnItemClickListener(l : View.OnClickListener){
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view: View = LayoutInflater.from(context).inflate(R.layout.rv_item_btn, parent, false)
        view.setOnClickListener(onItemClick)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // flag에 따라 버튼 이미지 on/off 설정
        if(dataList[position].flag)
            holder.btnImg.setBackgroundResource(dataList[position].on)
        else
            holder.btnImg.setBackgroundResource(dataList[position].off)
    }

    // View Holder
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnImg: ImageView = itemView.findViewById(R.id.btn_item_iv) as ImageView
    }

}