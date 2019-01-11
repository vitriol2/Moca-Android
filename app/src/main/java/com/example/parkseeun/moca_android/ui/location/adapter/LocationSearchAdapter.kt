package com.example.parkseeun.moca_android.ui.location.adapter

import android.content.Context
import android.graphics.Color
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetLocationListResponseData
import kotlinx.android.synthetic.main.activity_location_search.view.*
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.textColorResource

class LocationSearchAdapter(
    val context: Context,
    val dataList: ArrayList<GetLocationListResponseData>,
    val totalButton :Button
) : RecyclerView.Adapter<LocationSearchAdapter.Holder>() {

    private lateinit var onItemClick: View.OnClickListener

    fun setOnItemClickListener(l: View.OnClickListener) {
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view: View = LayoutInflater.from(context).inflate(R.layout.rv_item_cafe_location, parent, false)
        view.setOnClickListener(onItemClick)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 뷰 바인딩
        holder.txt_search_location_name.text = dataList[position].place_name
        holder.txt_search_location_address.text = dataList[position].road_address_name
        holder.img_common_yellowcircle.visibility = View.INVISIBLE
        if (dataList[position].dot == true) {
            holder.img_common_yellowcircle.visibility = View.VISIBLE
            totalButton.isEnabled = true
            totalButton.setBackgroundResource(R.drawable.round_square_point_pink)
            totalButton.textColorResource = R.color.white
        } else {
            holder.img_common_yellowcircle.visibility = View.INVISIBLE
        }
    }

    // View Holder
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_search_location_name: TextView = itemView.findViewById(R.id.txt_search_location_name) as TextView
        val txt_search_location_address: TextView = itemView.findViewById(R.id.txt_search_location_address) as TextView
        val img_common_yellowcircle: ImageView = itemView.findViewById(R.id.img_common_yellowcircle) as ImageView
    }
}