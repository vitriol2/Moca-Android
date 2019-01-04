package com.example.parkseeun.moca_android.ui.detail

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.community.review_detail.ReviewDetailActivity
import com.example.parkseeun.moca_android.ui.detail.nearbyList.NearbyListActivity
import org.jetbrains.anko.find

class DetailNearbyAdapter(val context : Context, val dataList : ArrayList<DetailNearbyData>) : RecyclerView.Adapter<DetailNearbyAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailNearbyAdapter.Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_act_detail_nearby_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: DetailNearbyAdapter.Holder, position: Int) {
        // 스크린 너비에 따른 이미지 길이 설정

        holder.item.setOnClickListener {
            val intent = Intent(context, ReviewDetailActivity::class.java)
            context.startActivity(intent)
        }


        holder.item.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            context.startActivity(intent)
        }

        Glide.with(context).load(dataList[position].pic).into(holder.pic)


        holder.cafename.text = dataList[position].cafename
        holder.location.text = dataList[position].location
        holder.rating.rating = dataList[position].rating.toFloat()
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val item : RelativeLayout = itemView.findViewById(R.id.rl_rv_act_detail_nearby) as RelativeLayout
        val pic : ImageView = itemView.findViewById(R.id.iv_rv_act_datail_nearby_photo) as ImageView
        val cafename : TextView = itemView.findViewById(R.id.tv_rv_act_detail_nearby_name) as TextView
        val location : TextView = itemView.findViewById(R.id.tv_rv_act_detail_nearby_location) as TextView
        val rating : RatingBar = itemView.findViewById(R.id.rb_rv_act_detail_nearby_rating) as RatingBar
    }
    private fun dpToPx(dp:Float):Float{
        return (dp * context.resources.displayMetrics.density)
    }
    private fun getScreenWidth():Int{
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }
}