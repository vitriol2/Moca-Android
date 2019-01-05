package com.example.parkseeun.moca_android.ui.detail.detailReviewAll

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.community.review_detail.ReviewDetailActivity
import kotlinx.android.synthetic.main.category_picks_rv_item.view.*
import org.jetbrains.anko.startActivity

class ReviewAllRecentAdapter(val ctx : Context, val dataList: ArrayList<ReviewAllRecentData>) : RecyclerView.Adapter<ReviewAllRecentAdapter.Holder>(){
    override fun onCreateViewHolder(container: ViewGroup, p1: Int): ReviewAllRecentAdapter.Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_act_review_all_recent_item, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ReviewAllRecentAdapter.Holder, position: Int) {

        Glide.with(ctx).load(dataList[position].photo).into(holder.photo)
        holder.photo.setOnClickListener {
            ctx.startActivity<ReviewDetailActivity>()
        }

    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val photo : ImageView = itemView.findViewById(R.id.iv_rv_act_review_all_recent_image) as ImageView
    }
}