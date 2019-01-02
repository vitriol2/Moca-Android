package com.example.parkseeun.moca_android.ui.main

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    // RecyclerView 설정
    lateinit var searchResultAdapter : SearchAdapater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setRecyclerView()
        categoryBtnListener()
    }

    private fun categoryBtnListener() {
        btn_all_category.setOnClickListener {
            btn_all_category.setTextColor(Color.parseColor("#e1b2a3"))
            btn_cafe_category.setTextColor(Color.parseColor("#707070"))
            btn_location_category.setTextColor(Color.parseColor("#707070"))

            view_all.setBackgroundColor(Color.parseColor("#e1b2a3"))
            view_cafe.setBackgroundColor(Color.parseColor("#ffffff"))
            view_location.setBackgroundColor(Color.parseColor("#ffffff"))

        }

        btn_cafe_category.setOnClickListener {
            btn_all_category.setTextColor(Color.parseColor("#707070"))
            btn_cafe_category.setTextColor(Color.parseColor("#e1b2a3"))
            btn_location_category.setTextColor(Color.parseColor("#707070"))

            view_all.setBackgroundColor(Color.parseColor("#ffffff"))
            view_cafe.setBackgroundColor(Color.parseColor("#e1b2a3"))
            view_location.setBackgroundColor(Color.parseColor("#ffffff"))
        }

        btn_location_category.setOnClickListener {
            btn_all_category.setTextColor(Color.parseColor("#707070"))
            btn_cafe_category.setTextColor(Color.parseColor("#707070"))
            btn_location_category.setTextColor(Color.parseColor("#e1b2a3"))

            view_all.setBackgroundColor(Color.parseColor("#ffffff"))
            view_cafe.setBackgroundColor(Color.parseColor("#ffffff"))
            view_location.setBackgroundColor(Color.parseColor("#e1b2a3"))
        }
    }

    private fun setRecyclerView() {
        // 임시 데이터
        var dataList : ArrayList<SearchResultData> = ArrayList()
        dataList.add(SearchResultData("", "빙봉", "크리스마스 화이링~~"))
        dataList.add(SearchResultData("", "빙봉", "크리스마스 화이링~~"))
        dataList.add(SearchResultData("", "빙봉", "크리스마스 화이링~~"))
        dataList.add(SearchResultData("", "빙봉", "크리스마스 화이링~~"))

        searchResultAdapter = SearchAdapater(this, dataList)
        rv_searchResult_list.adapter = searchResultAdapter
        rv_searchResult_list.layoutManager = LinearLayoutManager(applicationContext)
    }
}
