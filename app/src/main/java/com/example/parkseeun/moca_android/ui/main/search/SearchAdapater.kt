package com.example.parkseeun.moca_android.ui.main.search

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetHomeSearchResponseData
import com.example.parkseeun.moca_android.ui.detail.DetailActivity
import de.hdodenhof.circleimageview.CircleImageView

class SearchAdapater(val context : Context, val dataList : ArrayList<GetHomeSearchResponseData>) : RecyclerView.Adapter<SearchAdapater.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_search, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 뷰 바인딩
        Glide.with(context).load(dataList[position].cafe_img_url).into(holder.civ_search_resultImage)
        holder.tv_search_cafeName.text = dataList[position].cafe_name
        holder.tv_search_cafeLocation.text = dataList[position].cafe_address_detail

        // 클릭 리스너
        holder.relative_search_result.setOnClickListener {
            val intent : Intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("cafe_id", dataList[position].cafe_id)

            context.startActivity(intent)
        }
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val relative_search_result : RelativeLayout = itemView.findViewById(R.id.relative_search_result) as RelativeLayout
        val civ_search_resultImage : CircleImageView = itemView.findViewById(R.id.civ_search_resultImage) as CircleImageView
        val tv_search_cafeName : TextView = itemView.findViewById(R.id.tv_search_cafeName) as TextView
        val tv_search_cafeLocation : TextView = itemView.findViewById(R.id.tv_search_cafeLocation) as TextView
    }
}