package com.example.parkseeun.moca_android.ui.main.coupon

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.parkseeun.moca_android.R

class CouponAdapter(val ctx : Context, val dataList : ArrayList<String>) : RecyclerView.Adapter<CouponAdapter.Holder>(){

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_coupon_item, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.date.text = dataList[position]
        holder.coupon.setOnClickListener {
            Log.v("vv", "vv")
            setDialog()
        }
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val date : TextView = itemView.findViewById(R.id.tv_rv_coupon_item_date) as TextView
        val coupon : ImageView = itemView.findViewById(R.id.iv_act_coupon) as ImageView
    }

    private fun setDialog() {
        CouponDialog2(ctx, "1", "2", "3", "4").show()

    }
}