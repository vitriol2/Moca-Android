package com.example.parkseeun.moca_android.ui.community.review_write

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.community.review_write.data.CafeListData
import com.example.parkseeun.moca_android.ui.community.review_write.adapter.SearchLocationListAdapter
import kotlinx.android.synthetic.main.activity_community_search_address.*

class ReviewSearchLocationActivity : AppCompatActivity() {

    // RecyclerView 설정
    lateinit var searchLocationListAdapter : SearchLocationListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_search_address)
        setRecyclerView()
        setOnClickListener()

    }

    fun setRecyclerView() {

        if(et_addreview_search_address.text.toString()==""){
            search_address_recycler_view.visibility = View.INVISIBLE
        }else{
            search_address_recycler_view.visibility = View.VISIBLE
        }
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

        searchLocationListAdapter = SearchLocationListAdapter(applicationContext!!, dataList)
        search_address_recycler_view.adapter = searchLocationListAdapter
        search_address_recycler_view.layoutManager = LinearLayoutManager(applicationContext)
    }

    fun setOnClickListener() {

    }
}