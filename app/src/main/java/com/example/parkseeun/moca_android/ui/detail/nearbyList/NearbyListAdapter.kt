package com.example.parkseeun.moca_android.ui.detail.nearbyList

import android.content.Context
import android.graphics.Point
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R

class NearbyListAdapter(val context : Context, val dataList : ArrayList<NearbyListData>) : RecyclerView.Adapter<NearbyListAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearbyListAdapter.Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_act_nearby_list_item, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: NearbyListAdapter.Holder, position: Int) {
        // 스크린 너비에 따른 이미지 길이 설정
        val width = getScreenWidth()
        holder.pic.layoutParams.width = ((width-dpToPx(40.toFloat()))).toInt()
        holder.pic.layoutParams.height = holder.pic.layoutParams.width-20
        holder.pic.clipToOutline = true
        Glide.with(context).load(dataList[position].photo).into(holder.pic)
        holder.cafename.text = dataList[position].cafename
        holder.location.text = dataList[position].location
        holder.rating.rating = dataList[position].rating.toFloat()
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val pic : ImageView = itemView.findViewById(R.id.iv_rv_act_nearby_list_item) as ImageView
        val cafename : TextView = itemView.findViewById(R.id.tv_rv_act_nearby_list_name) as TextView
        val location : TextView = itemView.findViewById(R.id.tv_rv_act_nearby_list_location) as TextView
        val rating : RatingBar = itemView.findViewById(R.id.rb_rv_act_nearby_rating) as RatingBar
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