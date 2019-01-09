package com.example.parkseeun.moca_android.ui.location.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.location.data.LocationCafeDetailData
import com.example.parkseeun.moca_android.ui.location.data.MarkerItem

class LocationMainAdapter(
    val context: Context,
    val dataList: ArrayList<LocationCafeDetailData>,
    val markerItem: ArrayList<MarkerItem>
) : RecyclerView.Adapter<LocationMainAdapter.Holder>() {

    private lateinit var onItemClick: View.OnClickListener

    fun setOnItemClickListener(l: View.OnClickListener) {
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view: View = LayoutInflater.from(context).inflate(R.layout.rv_item_nearby_cafe, parent, false)
        view.setOnClickListener(onItemClick)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 뷰 바인딩
        Glide.with(context).load(dataList[position].cafe_img_url).into(holder.circle_nearby_cafe)
        holder.tv_nearby_cafe_name.text = dataList[position].name
        holder.txt_nearby_how_close.text = dataList[position].distance

        if (dataList[position].selected == false) {
            holder.item_cv_nearby_cafe.setBackgroundResource(R.drawable.boarder_darkgray)
        } else {
            holder.item_cv_nearby_cafe.setBackgroundResource(R.drawable.boarder_pointpink)
        }
    }

    // View Holder
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rl_dialog_location_main: RelativeLayout =
            itemView.findViewById(R.id.rl_dialog_location_main) as RelativeLayout
        val item_cv_nearby_cafe : CardView = itemView.findViewById(R.id.item_cv_nearby_cafe) as CardView
        val circle_nearby_cafe: ImageView = itemView.findViewById(R.id.circle_nearby_cafe) as ImageView
        val tv_nearby_cafe_name: TextView = itemView.findViewById(R.id.tv_nearby_cafe_name) as TextView
        val txt_nearby_how_close: TextView = itemView.findViewById(R.id.txt_nearby_how_close) as TextView
    }

}