package com.example.parkseeun.moca_android.ui.ranking

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
import com.example.parkseeun.moca_android.model.get.GetRankingResponseData

class RankingViewAdapter(val context : Context, val dataList : ArrayList<GetRankingResponseData>) : RecyclerView.Adapter<RankingViewAdapter.Holder>() {
    private lateinit var onItemClick : View.OnClickListener

    fun setOnItemClickListener(l : View.OnClickListener){
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewAdapter.Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_ranking, parent, false)
        view.setOnClickListener(onItemClick)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: RankingViewAdapter.Holder, position: Int) {
        // 스크린 너비에 따른 이미지 길이 설정
        val width = getScreenWidth()
        holder.pic.layoutParams.width = ((width-dpToPx(26.toFloat()))/2).toInt()
        holder.pic.layoutParams.height = holder.pic.layoutParams.width

        Glide.with(context).load(dataList[position].cafe_menu_img_url).into(holder.pic)
        holder.cafename.text = dataList[position].cafe_name
        holder.rank.text = (position+1).toString()
        holder.location.text = dataList[position].address_district_name
        holder.rating.rating = dataList[position].cafe_rating_avg.toFloat()
        if(dataList[position].evaluated_cafe_is)
            holder.evaluated.visibility = View.VISIBLE
        else
            holder.evaluated.visibility = View.GONE
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val pic : ImageView = itemView.findViewById(R.id.rank_item_pic_iv) as ImageView
        val rank : TextView = itemView.findViewById(R.id.rank_item_rank_tv) as TextView
        val evaluated : ImageView = itemView.findViewById(R.id.rank_item_evaluated_iv) as ImageView
        val cafename : TextView = itemView.findViewById(R.id.rank_item_cafename_tv) as TextView
        val location : TextView = itemView.findViewById(R.id.rank_item_location_tv) as TextView
        val rating : RatingBar = itemView.findViewById(R.id.rank_item_rate_rating) as RatingBar
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