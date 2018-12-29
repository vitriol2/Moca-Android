package com.example.parkseeun.moca_android.ui.location

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.location.adapter.LocationMainAdapter
import com.example.parkseeun.moca_android.ui.location.data.LocationMainData
import kotlinx.android.synthetic.main.activity_location_main.*
import kotlinx.android.synthetic.main.dialog_location_main.*

class LocationMainActivity :AppCompatActivity (){

    // RecyclerView 설정
    lateinit var locationMainAdapter : LocationMainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_main)
        setRecyclerView()

    }




    // RecyclerView 설정
    private fun setRecyclerView() {

// 임시 데이터
        var dataList : ArrayList<LocationMainData> = ArrayList()
        dataList.add(LocationMainData("", "카페1","1m 이내"))
        dataList.add(LocationMainData("", "카페2","10m 이내"))

        locationMainAdapter = LocationMainAdapter(applicationContext, dataList)
        rv_act_location_main.adapter = locationMainAdapter
        rv_act_location_main.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

    }
}