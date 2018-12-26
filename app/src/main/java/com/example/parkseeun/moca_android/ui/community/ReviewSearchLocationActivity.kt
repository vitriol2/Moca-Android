package com.example.parkseeun.moca_android.ui.community

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.CafeListData
import com.example.parkseeun.moca_android.ui.community.adapter.SearchLocationListAdapter
import kotlinx.android.synthetic.main.activity_community_search_address.*
import kotlinx.android.synthetic.main.activity_community_writereview.*
import kotlinx.android.synthetic.main.rv_item_cafe_location.*
import org.jetbrains.anko.db.NULL

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
        // 임시 데이터
        if(et_addreview_search_address.text.toString()==""){
            search_address_recycler_view.visibility = View.INVISIBLE
        }else{
            search_address_recycler_view.visibility = View.VISIBLE
        }

        var dataList : ArrayList<CafeListData> = ArrayList()
        dataList.add(CafeListData("카페이름", "주소"))
        dataList.add(CafeListData("카페이름1", "주소1"))

        searchLocationListAdapter = SearchLocationListAdapter(applicationContext!!, dataList)
        search_address_recycler_view.adapter = searchLocationListAdapter
        search_address_recycler_view.layoutManager = LinearLayoutManager(applicationContext)
    }

    fun setOnClickListener() {
        img_back.setOnClickListener {
            finish()
        }
    }
}