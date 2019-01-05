package com.example.parkseeun.moca_android.ui.category

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.category.recyclerview.cafe_list.CafeListData
import com.example.parkseeun.moca_android.ui.category.recyclerview.cafe_list.CafeListViewAdapter
import com.example.parkseeun.moca_android.ui.category.recyclerview.cafe_list.OptionData
import com.example.parkseeun.moca_android.ui.category.recyclerview.cafe_list.OptionViewAdapter
import com.example.parkseeun.moca_android.ui.category.recyclerview.category.ButtonData
import kotlinx.android.synthetic.main.activity_cafe_list.*

class CafeListActivity : AppCompatActivity() {
    private lateinit var optionViewAdapter : OptionViewAdapter
    private lateinit var cafeViewAdapter : CafeListViewAdapter
    private var optionList : ArrayList<OptionData> = ArrayList()
    private var cafeList : ArrayList<CafeListData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe_list)

        cafelist_back_iv.setOnClickListener { finish() }

        setRecyclerView()
    }

    private fun setRecyclerView() {
        // 지역 옵션과 컨셉, 메뉴 옵션 추가
        optionList.add(OptionData(getRegionId(intent.getIntExtra("regionSelected", -1)), true))
        setListData(optionList, intent.getParcelableArrayListExtra("concept"))
        setListData(optionList, intent.getParcelableArrayListExtra("menu"))

        optionViewAdapter = OptionViewAdapter(this, optionList)
        cafelist_option_rv.adapter = optionViewAdapter
        cafelist_option_rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        cafeList.add(CafeListData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 하우스", "북극", 4, arrayListOf("한옥", "드라이브", "커피")))
        cafeList.add(CafeListData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 하우스", "북극", 4, arrayListOf("한옥", "드라이브", "커피")))
        cafeList.add(CafeListData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 하우스", "북극", 4, arrayListOf("한옥", "드라이브", "커피")))
        cafeList.add(CafeListData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 하우스", "북극", 4, arrayListOf("한옥", "드라이브", "커피")))
        cafeList.add(CafeListData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 하우스", "북극", 4, arrayListOf("한옥", "드라이브", "커피")))
        cafeList.add(CafeListData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 하우스", "북극", 4, arrayListOf("한옥", "드라이브", "커피")))

        cafeViewAdapter= CafeListViewAdapter(this, cafeList)
        cafelist_cafe_rv.adapter = cafeViewAdapter
        cafelist_cafe_rv.layoutManager = LinearLayoutManager(this)
    }

    private fun getRegionId(selected: Int):String{
        when(selected){
            0 -> return "강동구"
        }
        return "[잘못된 접근]"
    }

    private fun setListData(list: ArrayList<OptionData>, data: ArrayList<ButtonData>) {
        for(i in data){
            if(i.flag)
                list.add(OptionData(i.name))
        }
    }
}