package com.example.parkseeun.moca_android.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_hot_place.*
import kotlinx.android.synthetic.main.activity_search.*

class HotPlaceActivity : AppCompatActivity() {

    lateinit var hotPlaceAdapter: HotPlaceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hot_place)

        setUpRecyclerView()

        ib_hot_place_back.setOnClickListener {
            finish()
        }
    }

    private fun setUpRecyclerView() {
        var dataList : ArrayList<HotPlaceData> = ArrayList()
        var imgList : ArrayList<String> = ArrayList()

        imgList.add("http://img.etoday.co.kr/pto_db/2017/03/20170331060559_1043241_600_400.jpg")
        imgList.add("http://img.etoday.co.kr/pto_db/2017/03/20170331060559_1043241_600_400.jpg")
        imgList.add("http://img.etoday.co.kr/pto_db/2017/03/20170331060559_1043241_600_400.jpg")
        imgList.add("http://img.etoday.co.kr/pto_db/2017/03/20170331060559_1043241_600_400.jpg")
        imgList.add("http://img.etoday.co.kr/pto_db/2017/03/20170331060559_1043241_600_400.jpg")

        dataList.add(HotPlaceData("http://img.etoday.co.kr/pto_db/2017/03/20170331060559_1043241_600_400.jpg", "김재욱 짱", "망원역 도보 3분", 2, imgList))
        dataList.add(HotPlaceData("http://img.etoday.co.kr/pto_db/2017/03/20170331060559_1043241_600_400.jpg", "김재욱 짱", "망원역 도보 3분", 2, imgList))
        dataList.add(HotPlaceData("http://img.etoday.co.kr/pto_db/2017/03/20170331060559_1043241_600_400.jpg", "김재욱 짱", "망원역 도보 3분", 2, imgList))
        dataList.add(HotPlaceData("http://img.etoday.co.kr/pto_db/2017/03/20170331060559_1043241_600_400.jpg", "김재욱 짱", "망원역 도보 3분", 2, imgList))

        hotPlaceAdapter = HotPlaceAdapter(this, dataList)
        rv_hot_place.adapter = hotPlaceAdapter
        rv_hot_place.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

}
