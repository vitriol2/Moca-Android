package com.example.parkseeun.moca_android.ui.communitySearch

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetBestCafeListData
import com.example.parkseeun.moca_android.ui.detail.DetailActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class ComSearAllReviewTopAdapter(val context : Context, val dataList : ArrayList<GetBestCafeListData>) : RecyclerView.Adapter<ComSearAllReviewTopAdapter.Holder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComSearAllReviewTopAdapter.Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_frag_com_sear_all_reviewtop, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ComSearAllReviewTopAdapter.Holder, position: Int) {
        val cafe_id = dataList[position].cafe_id
        holder.item.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("cafe_id", cafe_id)
            context.startActivity(intent)
        }
        Glide.with(context).load(dataList[position].cafe_img_url!!).into(holder.image!!)
        holder.cafename.text = dataList[position].cafe_name

        holder.reviewNum.text = dataList[position].review_count.toString()

    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val item : RelativeLayout = itemView.findViewById(R.id.rl_frag_com_sear_all_reviewtop) as RelativeLayout
        val image : ImageView? = itemView.findViewById(R.id.iv_rv_frag_com_sear_all_reviewtop) as ImageView?
        val cafename : TextView = itemView.findViewById(R.id.tv_rv_frag_com_sear_all_reviewtop) as TextView
        val reviewNum : TextView = itemView.findViewById(R.id.tv_rv_frag_com_sear_all_reviewtop_reviewnum) as TextView
    }
}