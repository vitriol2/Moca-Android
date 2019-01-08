package com.example.parkseeun.moca_android.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.MocaplusData

class HomeMocaplusAdapter(val ctx : Context, val dataList: ArrayList<MocaplusData>) : RecyclerView.Adapter<HomeMocaplusAdapter.Holder>(){
    override fun onCreateViewHolder(container: ViewGroup, p1: Int): HomeMocaplusAdapter.Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_act_home_mocaplus, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: HomeMocaplusAdapter.Holder, position: Int) {
        Glide.with(ctx).load(dataList[position].plus_subject_img_url).into(holder.image)
        holder.title.text = dataList[position].plus_subject_title
        holder.editor.text = dataList[position].editor_name
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.iv_rv_act_home_mocaplus_image) as ImageView
        val title : TextView = itemView.findViewById(R.id.tv_rv_act_home_mocaplus_title) as TextView
        val editor : TextView = itemView.findViewById(R.id.tv_rv_act_home_mocaplus_editor) as TextView

    }
}