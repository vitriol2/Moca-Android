package com.example.parkseeun.moca_android.ui.category

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetCafeListResponse
import com.example.parkseeun.moca_android.model.get.GetCafeListResponseData
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.category.recyclerview.cafe_list.CafeListViewAdapter
import com.example.parkseeun.moca_android.ui.category.recyclerview.cafe_list.OptionData
import com.example.parkseeun.moca_android.ui.category.recyclerview.cafe_list.OptionViewAdapter
import com.example.parkseeun.moca_android.ui.category.recyclerview.category.ButtonData
import kotlinx.android.synthetic.main.activity_cafe_list.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CafeListActivity : AppCompatActivity() {
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var getCafeListResponse: Call<GetCafeListResponse>
    private lateinit var optionViewAdapter : OptionViewAdapter
    private lateinit var cafeViewAdapter : CafeListViewAdapter
    private var optionList : ArrayList<OptionData> = ArrayList()
    private var cafeList : ArrayList<GetCafeListResponseData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe_list)

        cafelist_back_iv.setOnClickListener { finish() }

        setRecyclerView()
        
        communicate()
    }

    private fun setRecyclerView() {
        // 지역 옵션과 컨셉, 메뉴 옵션 추가
        optionList.add(OptionData(intent.getIntExtra("regionSelected", -1) ,getRegionId(intent.getIntExtra("regionSelected", -1)), "region", true))
        setListData(optionList, intent.getParcelableArrayListExtra("concept"), "concept")
        setListData(optionList, intent.getParcelableArrayListExtra("menu"), "menu")

        optionViewAdapter = OptionViewAdapter(this, optionList)
        cafelist_option_rv.adapter = optionViewAdapter
        cafelist_option_rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun getRegionId(selected: Int):String{
        val regionData = arrayOf("종로구", "중구", "용산구", "성동구", "광진구", "동대문구", "중랑구", "성북구", "강북구", "도봉구", "노원구", "은평구", "서대문구", "마포구", "양천구", "강서구", "구로구", "금천구", "영등포구", "동작구", "관악구", "서초구", "강남구", "송파구", "강동구")
        if (selected in 0..24)
            return regionData[selected]
        return "[잘못된 접근]"
    }

    private fun setListData(list: ArrayList<OptionData>, data: ArrayList<ButtonData>, category: String) {
        for(i in data){
            if(i.flag)
                list.add(OptionData(i.id, i.name, category))
        }
    }

    private fun communicate(){
        getCafeListResponse = networkService.getCafeList(optionList[0].id+1, getOptionIds("concept"), getOptionIds("menu"))
        getCafeListResponse.enqueue(object :Callback<GetCafeListResponse> {
            override fun onFailure(call: Call<GetCafeListResponse>?, t: Throwable?) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetCafeListResponse>?, response: Response<GetCafeListResponse>?) {
                if (response!!.body()!!.status == 200) {
                    cafeList = response.body()!!.data
                    cafeViewAdapter= CafeListViewAdapter(this@CafeListActivity, cafeList)
                    cafelist_cafe_rv.adapter = cafeViewAdapter
                    cafelist_cafe_rv.layoutManager = LinearLayoutManager(this@CafeListActivity)
                } else if(response!!.body()!!.status == 404) {
                    toast("조건에 일치하는 카페가 없습니다")
                } else {
                    toast(response.body()!!.status.toString() + ": " + response.body()!!.message)
                }
            }
        })
    }

    private fun getOptionIds(category: String):List<Int>{
        var tmp = arrayListOf<Int>()
        for(option in optionList){
            if(option.category == category)
                tmp.add(option.id)
        }
        return tmp.toList()
    }
}