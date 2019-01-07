package com.example.parkseeun.moca_android.ui.mypage.coupon.recyclerview

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetMembershipResponseData
import de.hdodenhof.circleimageview.CircleImageView

class CouponHistoryViewAdapter(val context: Context, val dataList: ArrayList<GetMembershipResponseData>) : RecyclerView.Adapter<CouponHistoryViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.rv_item_coupon, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        when((dataList.size-position)%12){
            // bottom, plain, top 별 background, margin 설정
            0 -> (holder.bg.layoutParams as ConstraintLayout.LayoutParams).let {
                it.bottomMargin = (20.0f*context.resources.displayMetrics.density).toInt()
                holder.bg.layoutParams = it
                holder.bg.setBackgroundResource(R.drawable.square_coupon_bottom)
            }
            1 -> (holder.bg.layoutParams as ConstraintLayout.LayoutParams).let {
                it.bottomMargin = (6.0f*context.resources.displayMetrics.density).toInt()
                holder.bg.layoutParams = it
                holder.bg.setBackgroundResource(R.drawable.square_coupon_top)
            }
            else -> (holder.bg.layoutParams as ConstraintLayout.LayoutParams).let {
                it.bottomMargin = (6.0f*context.resources.displayMetrics.density).toInt()
                holder.bg.layoutParams = it
                holder.bg.setBackgroundResource(R.color.point_pink)
            }
        }
        Glide.with(context).load(dataList[position].cafe_img_url).into(holder.img)
        holder.name.text = dataList[position].cafe_id.toString()+"번 카페 이름은 모르긔"
        holder.date.text = dataList[position].membership_create_date.substring(0,10).replace("-",".")
    }

    // View Holder
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bg = itemView.findViewById(R.id.history_item_bg_const) as ConstraintLayout
        val img = itemView.findViewById(R.id.history_item_img_ci) as CircleImageView
        val name = itemView.findViewById(R.id.history_item_name_tv) as TextView
        val date = itemView.findViewById(R.id.history_date_tv) as TextView
    }
}