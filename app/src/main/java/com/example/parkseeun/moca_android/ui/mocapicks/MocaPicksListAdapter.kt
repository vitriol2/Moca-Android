package com.example.parkseeun.moca_android.ui.mocapicks

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R

class MocaPicksListAdapter(val context : Context, val dataList : ArrayList<MocaPicksListData>) : RecyclerView.Adapter<MocaPicksListAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MocaPicksListAdapter.Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_mocapicks_list, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MocaPicksListAdapter.Holder, position: Int) {
        // 뷰 바인딩
        Glide.with(context).load(dataList[position].cafe_img_main).into(holder.iv_mocaPicksList_cafeImage)
        holder.tv_mocaPicksList_cafeName.text = dataList[position].cafe_name
        holder.tv_mocaPicksList_cafeLocation.text = dataList[position].address_district_name

        // 토글 버튼 리스너
        holder.tb_mocaPicksList.setOnClickListener {

        }

        // 아이템 누르면 액티비티 전환
        holder.relative_mocaPicks_rv_item.setOnClickListener {
            val intent : Intent = Intent(context, MocaPicksDetailActivity::class.java)

            context.startActivity(intent)
        }
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val relative_mocaPicks_rv_item : RelativeLayout = itemView.findViewById(R.id.relative_mocaPicks_rv_item) as RelativeLayout
        val iv_mocaPicksList_cafeImage : ImageView = itemView.findViewById(R.id.iv_mocaPicksList_cafeImage) as ImageView
        val tb_mocaPicksList : ToggleButton = itemView.findViewById(R.id.tb_mocaPicksList) as ToggleButton
        val tv_mocaPicksList_cafeName : TextView = itemView.findViewById(R.id.tv_mocaPicksList_cafeName) as TextView
        val tv_mocaPicksList_cafeLocation : TextView = itemView.findViewById(R.id.tv_mocaPicksList_cafeLocation) as TextView
        val rating_mocaPicksList : RatingBar = itemView.findViewById(R.id.rating_mocaPicksList) as RatingBar
    }
}