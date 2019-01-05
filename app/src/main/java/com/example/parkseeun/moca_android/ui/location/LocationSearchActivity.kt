package com.example.parkseeun.moca_android.ui.location

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.community.review_write.data.CafeListData
import com.example.parkseeun.moca_android.ui.location.adapter.LocationSearchAdapter
import kotlinx.android.synthetic.main.activity_location_search.*

class LocationSearchActivity : AppCompatActivity() {

    // RecyclerView 설정
    lateinit var locationSearchAdapter : LocationSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_search)
        setRecyclerView()

        setOnBtnClickListeners()
    }

    private fun setOnBtnClickListeners() {
        img_location_common_backbtn_black.setOnClickListener {
            finish()
        }
    }

    fun setRecyclerView() {

//        if(et_addreview_search_address.text.toString()==""){
//            search_address_recycler_view.visibility = View.INVISIBLE
//        }else{
//            search_address_recycler_view.visibility = View.VISIBLE
//        }

// 임시 데이터
        var dataList : ArrayList<CafeListData> = ArrayList()
        dataList.add(CafeListData("카페이름", "주소"))
        dataList.add(CafeListData("카페이름1", "주소1"))
        dataList.add(CafeListData("카페이름1", "주소1"))
        dataList.add(CafeListData("카페이름1", "주소1"))
        dataList.add(CafeListData("카페이름1", "주소1"))
        dataList.add(CafeListData("카페이름1", "주소1"))
        dataList.add(CafeListData("카페이름1", "주소1"))
        dataList.add(CafeListData("카페이름1", "주소1"))
        dataList.add(CafeListData("카페이름1", "주소1"))

        locationSearchAdapter = LocationSearchAdapter(this, dataList)
        rv_act_location_search.adapter = locationSearchAdapter
        rv_act_location_search.layoutManager = LinearLayoutManager(applicationContext)
    }
}