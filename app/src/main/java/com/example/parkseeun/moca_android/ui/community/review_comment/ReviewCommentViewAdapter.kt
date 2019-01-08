package com.example.parkseeun.moca_android.ui.community.review_comment

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetReviewCommentResponseData
import com.example.parkseeun.moca_android.ui.community.feed.FeedActivity
import com.example.parkseeun.moca_android.ui.community.feed.other_user.OtherUserActivity
import com.example.parkseeun.moca_android.util.User
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
        // 이름과 프로필 사진 누르면 본인/타인에 따라 각자의 피드로 이동
        View.OnClickListener {
            if(dataList[position].user_id == User.user_id){
                Intent(context, FeedActivity::class.java).apply { putExtra("myFeed", true); context.startActivity(this) }
            }else{
                Intent(context, OtherUserActivity::class.java).apply { putExtra("user_id", dataList[position].user_id); context.startActivity(this) }
            }
        }.let {
            holder.civ_comment_profileImage.setOnClickListener(it)
            holder.tv_comment_name.setOnClickListener(it)
        }
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val civ_comment_profileImage : CircleImageView = itemView.findViewById(R.id.civ_comment_profileImage) as CircleImageView
        val tv_comment_name : TextView = itemView.findViewById(R.id.tv_comment_name) as TextView
        val tv_comment_content : TextView = itemView.findViewById(R.id.tv_comment_content) as TextView
        val tv_comment_time : TextView = itemView.findViewById(R.id.tv_comment_time) as TextView
    }
}