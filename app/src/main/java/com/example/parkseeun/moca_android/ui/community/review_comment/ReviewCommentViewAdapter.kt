package com.example.parkseeun.moca_android.ui.community.review_comment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetReviewCommentResponseData
import de.hdodenhof.circleimageview.CircleImageView

class ReviewCommentViewAdapter(val context : Context, val dataList : ArrayList<GetReviewCommentResponseData>) : RecyclerView.Adapter<ReviewCommentViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewCommentViewAdapter.Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_comment, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ReviewCommentViewAdapter.Holder, position: Int) {
        // 뷰 바인딩
        Glide.with(context).load(dataList[position].user_img_url).into(holder.civ_comment_profileImage) // 이게 맞을깡
        holder.tv_comment_name.text = dataList[position].user_name
        holder.tv_comment_content.text = dataList[position].review_comment_content
        holder.tv_comment_time.text = dataList[position].time
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val civ_comment_profileImage : CircleImageView = itemView.findViewById(R.id.civ_comment_profileImage) as CircleImageView
        val tv_comment_name : TextView = itemView.findViewById(R.id.tv_comment_name) as TextView
        val tv_comment_content : TextView = itemView.findViewById(R.id.tv_comment_content) as TextView
        val tv_comment_time : TextView = itemView.findViewById(R.id.tv_comment_time) as TextView
    }
}