package com.example.parkseeun.moca_android.ui.plus.plusDetail

import android.content.Context
import android.content.Intent
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.detail.DetailActivity
import com.example.parkseeun.moca_android.util.ImageAdapter
import kotlinx.android.synthetic.main.activity_moca_picks_detail.*
import com.example.parkseeun.moca_android.util.gravitySnapHelper.GravitySnapHelper


class PlusDetailRecyclerViewAdapter(val context : Context, val dataList : ArrayList<PlusDetailData>) : RecyclerView.Adapter<PlusDetailRecyclerViewAdapter.Holder>() {

    // Image RecyclerView
    lateinit var linearLayoutManager:LinearLayoutManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_plus_detail, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 뷰 바인딩
        holder.tv_plus_detail_item_location.text = dataList[position].location
        holder.tv_plus_detail_item_cafeName.text = dataList[position].cafeName
        holder.tv_plus_detail_item_cafeName2.text = dataList[position].cafeName
        holder.tv_plus_detail_item_contents.text = dataList[position].contents

        // ㅜ-ㅜ
        holder.vp_mocaPlusDetail.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) { // 이미지 넘길 떄 하는 일은 여기서,,
                holder.progressBar_plus_detail_item.progress = p0 + 1
            }
        })
        var urlList: Array<String?> = arrayOf("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG","http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG")
        holder.progressBar_plus_detail_item.max = urlList.size

        holder.vp_mocaPlusDetail.adapter = ImageAdapter(context, urlList)

        // 클릭
        holder.relative_plus_detail_item_goToCafe.setOnClickListener {
            // 해당 카페 상세 페이지로 이동
            val intent : Intent = Intent(context, DetailActivity::class.java)

            context.startActivity(intent)
        }
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tv_plus_detail_item_location : TextView = itemView.findViewById(R.id.tv_plus_detail_item_location) as TextView
        val tv_plus_detail_item_cafeName : TextView = itemView.findViewById(R.id.tv_plus_detail_item_cafeName) as TextView
        val tv_plus_detail_item_contents : TextView = itemView.findViewById(R.id.tv_plus_detail_item_contents) as TextView
        val tv_plus_detail_item_cafeName2 : TextView = itemView.findViewById(R.id.tv_plus_detail_item_cafeName2) as TextView
        val vp_mocaPlusDetail : ViewPager = itemView.findViewById(R.id.vp_mocaPlusDetail) as ViewPager
        val progressBar_plus_detail_item : ProgressBar = itemView.findViewById(R.id.progressBar_plus_detail_item) as ProgressBar
        val relative_plus_detail_item_goToCafe : RelativeLayout = itemView.findViewById(R.id.relative_plus_detail_item_goToCafe) as RelativeLayout
    }
}