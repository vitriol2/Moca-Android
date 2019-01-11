package com.example.parkseeun.moca_android.ui.community.review_write.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetHomeSearchResponseData
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.textColorResource

class ReviewSearchLocationListAdapter(
    val context: Context,
    val dataList: ArrayList<GetHomeSearchResponseData>,
    val totalButton: Button
) : RecyclerView.Adapter<ReviewSearchLocationListAdapter.Holder>() {
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
        holder.txt_search_location_name.text = dataList[position].cafe_name
        holder.txt_search_location_address.text = dataList[position].cafe_address_detail
        holder.img_common_yellowcircle.visibility = View.INVISIBLE
        if (dataList[position].dot == true) {
            holder.img_common_yellowcircle.visibility = View.VISIBLE
            //totalButton!!.isEnabled = true
            totalButton!!.backgroundResource = R.drawable.round_square_point_pink
            totalButton!!.textColorResource =R.color.white
        } else {
            holder.img_common_yellowcircle.visibility = View.INVISIBLE
            //totalButton!!.isEnabled = false
            totalButton!!.backgroundResource = R.drawable.round_square_lightgray
            totalButton!!.textColorResource = R.color.dark_gray
        }

    }

    // View Holder
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_search_location_name: TextView = itemView.findViewById(R.id.txt_search_location_name) as TextView
        val txt_search_location_address: TextView = itemView.findViewById(R.id.txt_search_location_address) as TextView
        val img_common_yellowcircle: ImageView = itemView.findViewById(R.id.img_common_yellowcircle) as ImageView
    }
}