package com.example.parkseeun.moca_android.ui.plus

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.MocaplusData
import com.example.parkseeun.moca_android.ui.plus.plusDetail.PlusDetailActivity
import de.hdodenhof.circleimageview.CircleImageView

class PlusRecyclerViewAdapter(val context : Context, val dataList : ArrayList<MocaplusData>) : RecyclerView.Adapter<PlusRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_plus, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 뷰 바인딩
        Glide.with(context).load(dataList[position].plus_subject_img_url).into(holder.iv_plus_contentsImage)
        Glide.with(context).load(dataList[position].editor_img_url).into(holder.civ_plus_profile)
        holder.tv_plus_title.text = dataList[position].plus_subject_title
        holder.tv_plus_editorName.text = dataList[position].editor_name

        // 클릭
        holder.linearLayout_plus_item.setOnClickListener {
            Intent(context, PlusDetailActivity::class.java).apply{
                putExtra("plus_subject_id", dataList[position].plus_subject_id)
                context.startActivity(this)

            }
        }
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val linearLayout_plus_item : LinearLayout = itemView.findViewById(R.id.linearLayout_plus_item) as LinearLayout
        val iv_plus_contentsImage : ImageView = itemView.findViewById(R.id.iv_plus_contentsImage) as ImageView
        val civ_plus_profile : CircleImageView = itemView.findViewById(R.id.civ_plus_profile) as CircleImageView
        val tv_plus_title : TextView = itemView.findViewById(R.id.tv_plus_title) as TextView
        val tv_plus_editorName : TextView = itemView.findViewById(R.id.tv_plus_editorName) as TextView
    }
}