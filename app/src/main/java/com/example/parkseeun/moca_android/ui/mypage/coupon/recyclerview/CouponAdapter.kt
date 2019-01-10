package com.example.parkseeun.moca_android.ui.mypage.coupon.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.CouponData
import com.example.parkseeun.moca_android.ui.mypage.coupon.CouponDialog

class CouponAdapter(val ctx : Context, val dataList : ArrayList<CouponData>) : RecyclerView.Adapter<CouponAdapter.Holder>(){

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_coupon_item, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.date.text = "2018.12.22"             // 임시 date 세팅
        holder.coupon.setOnClickListener {
            setDialog(dataList[position].coupon_authentication_number)
        }
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val date : TextView = itemView.findViewById(R.id.tv_rv_coupon_item_date) as TextView
        val coupon : ImageView = itemView.findViewById(R.id.iv_act_coupon) as ImageView
    }

    private fun setDialog(num: String) {
        CouponDialog(ctx, "${num[0]}", "${num[1]}", "${num[2]}", "${num[3]}").show()

    }
}