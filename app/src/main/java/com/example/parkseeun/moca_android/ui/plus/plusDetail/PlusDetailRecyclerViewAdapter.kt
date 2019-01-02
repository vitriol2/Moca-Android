package com.example.parkseeun.moca_android.ui.plus.plusDetail

import android.content.Context
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
import com.example.parkseeun.moca_android.util.gravitySnapHelper.GravitySnapHelper


class PlusDetailRecyclerViewAdapter(val context : Context, val dataList : ArrayList<PlusDetailData>) : RecyclerView.Adapter<PlusDetailRecyclerViewAdapter.Holder>() {

    // Image RecyclerView
    lateinit var plusDetailImageRecyclerViewAdapter: PlusDetailImageRecyclerViewAdapter
//    var snapHelper = LinearSnapHelper()
    lateinit var snapHelper: SnapHelper
    lateinit var linearLayoutManager:LinearLayoutManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_plus_detail, parent, false)

        setImageRecyclerView(Holder(view))

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


        // 클릭
        holder.relative_plus_detail_item_goToCafe.setOnClickListener {
            // 해당 카페 상세 페이지로 이동

        }
    }

    private fun setImageRecyclerView(holder: Holder) {
        // 임시 데이터,,,
        var dataList : ArrayList<String> = ArrayList()

        dataList.add("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG")
        dataList.add("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG")
        dataList.add("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG")
        dataList.add("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG")

        plusDetailImageRecyclerViewAdapter = PlusDetailImageRecyclerViewAdapter(context!!, dataList)
        holder.rv_plus_detail_item.adapter = plusDetailImageRecyclerViewAdapter
        linearLayoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        holder.rv_plus_detail_item.layoutManager = linearLayoutManager

        snapHelper = GravitySnapHelper(Gravity.START)
        snapHelper.attachToRecyclerView(holder.rv_plus_detail_item)

        holder.progressBar_plus_detail_item.max = dataList.size

        //
        var firstVisibleInListview = linearLayoutManager.findFirstVisibleItemPosition()

//        holder.rv_plus_detail_item.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//
//                var currentFirstVisible = linearLayoutManager.findFirstVisibleItemPosition()
//
//                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
//                    Log.v("hihi", "first : " + firstVisibleInListview)
//                    Log.v("hihi", "current : " + currentFirstVisible)
//
//                    if(currentFirstVisible > firstVisibleInListview) {
//                        Log.v("dd", "up")
//                        holder.progressBar_plus_detail_item.progress += 1
//                    }
//                    else {
//                        Log.v("dd", "down")
//                        holder.progressBar_plus_detail_item.progress -= 1
//                    }
//                    firstVisibleInListview = currentFirstVisible
////                    holder.progressBar_plus_detail_item.progress += 1
//                }
//            }
//        })

    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tv_plus_detail_item_location : TextView = itemView.findViewById(R.id.tv_plus_detail_item_location) as TextView
        val tv_plus_detail_item_cafeName : TextView = itemView.findViewById(R.id.tv_plus_detail_item_cafeName) as TextView
        val tv_plus_detail_item_contents : TextView = itemView.findViewById(R.id.tv_plus_detail_item_contents) as TextView
        val tv_plus_detail_item_cafeName2 : TextView = itemView.findViewById(R.id.tv_plus_detail_item_cafeName2) as TextView
        val rv_plus_detail_item : RecyclerView = itemView.findViewById(R.id.rv_plus_detail_item) as RecyclerView
        val progressBar_plus_detail_item : ProgressBar = itemView.findViewById(R.id.progressBar_plus_detail_item) as ProgressBar
        val relative_plus_detail_item_goToCafe : RelativeLayout = itemView.findViewById(R.id.relative_plus_detail_item_goToCafe) as RelativeLayout
    }
}