package com.example.parkseeun.moca_android.ui.main

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.MocaplusData
import com.example.parkseeun.moca_android.ui.plus.plusDetail.PlusDetailActivity

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

        holder.relative_home_moca_plus.setOnClickListener {
            val intent = Intent(ctx, PlusDetailActivity::class.java)

            intent.putExtra("plus_subject_id", dataList[position].plus_subject_id)

            ctx.startActivity(intent)
        }
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val relative_home_moca_plus : RelativeLayout = itemView.findViewById(R.id.relative_home_moca_plus) as RelativeLayout
        val image : ImageView = itemView.findViewById(R.id.iv_rv_act_home_mocaplus_image) as ImageView
        val title : TextView = itemView.findViewById(R.id.tv_rv_act_home_mocaplus_title) as TextView
        val editor : TextView = itemView.findViewById(R.id.tv_rv_act_home_mocaplus_editor) as TextView

    }
}