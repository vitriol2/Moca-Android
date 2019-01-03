package com.example.parkseeun.moca_android.ui.location.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.R.drawable.round_square_white
import com.example.parkseeun.moca_android.ui.location.LocationMainDialog
import com.example.parkseeun.moca_android.ui.location.data.LocationMainData
import com.example.parkseeun.moca_android.ui.location.data.MarkerItem
import org.jetbrains.anko.backgroundResource

class LocationMainAdapter(val context : Context, val dataList : ArrayList<LocationMainData>,val markerItem: ArrayList<MarkerItem> ) : RecyclerView.Adapter<LocationMainAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_nearby_cafe, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 뷰 바인딩
        Glide.with(context).load(dataList[position].cafeImageUrl).into(holder.circle_nearby_cafe)
        holder.tv_nearby_cafe_name.text = dataList[position].name
        holder.txt_nearby_how_close.text = dataList[position].address

        holder.rl_dialog_location_main.setOnClickListener {
            if (dataList[position].flag == 0) {
                markerItem.get(position).setmarker(true)
                dataList[position].flag = 1
                holder.rl_dialog_location_main.setBackgroundResource(R.drawable.boarder_pointpink)
            }
            else if (dataList[position].flag == 1) {
                val dialog: LocationMainDialog = LocationMainDialog(context, dataList[position])
                Log.v("플래그 (어댑터)", "" + dataList[position].flag)
                holder.rl_dialog_location_main.setBackgroundResource(R.drawable.boarder_darkgray)
                //val dialog : LocationMainDialog = LocationMainDialog(context as LocationMainActivity)
                dialog.show()
            }
        }
    }
    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val rl_dialog_location_main : RelativeLayout = itemView.findViewById(R.id.rl_dialog_location_main) as RelativeLayout
        val circle_nearby_cafe : ImageView = itemView.findViewById(R.id.circle_nearby_cafe) as ImageView
        val tv_nearby_cafe_name : TextView = itemView.findViewById(R.id.tv_nearby_cafe_name) as TextView
        val txt_nearby_how_close : TextView = itemView.findViewById(R.id.txt_nearby_how_close) as TextView
    }

}