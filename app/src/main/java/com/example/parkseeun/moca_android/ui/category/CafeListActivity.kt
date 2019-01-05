package com.example.parkseeun.moca_android.ui.category

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.category.recyclerview.CafeListData
import com.example.parkseeun.moca_android.ui.category.recyclerview.CafeListViewAdapter
import com.example.parkseeun.moca_android.ui.category.recyclerview.OptionData
import com.example.parkseeun.moca_android.ui.category.recyclerview.OptionViewAdapter
import kotlinx.android.synthetic.main.activity_cafe_list.*

class CafeListActivity : AppCompatActivity() {
    private lateinit var optionViewAdapter : OptionViewAdapter
    private lateinit var cafeViewAdapter : CafeListViewAdapter
    private var optionList : ArrayList<OptionData> = ArrayList()
    private var cafeList : ArrayList<CafeListData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe_list)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        optionList.add(OptionData("강동구", true))
        optionList.add(OptionData("감성"))
        optionList.add(OptionData("드라이브"))
        optionList.add(OptionData("커피"))
        optionList.add(OptionData("커피"))
        optionList.add(OptionData("커피"))
        optionList.add(OptionData("커피"))
        optionList.add(OptionData("커피"))
        optionList.add(OptionData("커피"))

        optionViewAdapter = OptionViewAdapter(this, optionList)
        cafelist_option_rv.adapter = optionViewAdapter
        cafelist_option_rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        cafeList.add(CafeListData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 하우스","북극", 4, arrayListOf("한옥","드라이브","커피")))
        cafeList.add(CafeListData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 하우스","북극", 4, arrayListOf("한옥","드라이브","커피")))
        cafeList.add(CafeListData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 하우스","북극", 4, arrayListOf("한옥","드라이브","커피")))
        cafeList.add(CafeListData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 하우스","북극", 4, arrayListOf("한옥","드라이브","커피")))
        cafeList.add(CafeListData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 하우스","북극", 4, arrayListOf("한옥","드라이브","커피")))
        cafeList.add(CafeListData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 하우스","북극", 4, arrayListOf("한옥","드라이브","커피")))

        cafeViewAdapter= CafeListViewAdapter(this, cafeList)
        cafelist_cafe_rv.adapter = cafeViewAdapter
        cafelist_cafe_rv.layoutManager = LinearLayoutManager(this)
    }
}