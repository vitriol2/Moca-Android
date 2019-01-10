package com.example.parkseeun.moca_android.ui.community.review_write

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetHomeSearchResponse
import com.example.parkseeun.moca_android.model.get.GetHomeSearchResponseData
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.network.NetworkService
import com.example.parkseeun.moca_android.ui.community.review_write.adapter.SearchLocationListAdapter
import kotlinx.android.synthetic.main.activity_community_search_address.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewSearchLocationActivity : AppCompatActivity() {
    private val networkService  = ApplicationController.instance.networkService
    // 검색
    private lateinit var getHomeSearchResponse : Call<GetHomeSearchResponse>
    var dataList : ArrayList<GetHomeSearchResponseData> = ArrayList()
    private var getHomeSearchResponseData = ArrayList<GetHomeSearchResponseData>()
    // RecyclerView 설정
    lateinit var searchLocationListAdapter: SearchLocationListAdapter
    private var cafe_name : String? =null
    private var cafe_id : Int? = null
    private var cafe_address : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_search_address)
        setOnClickListener()

        et_addreview_search_address.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != "") {
                    getSearchResult(applicationContext, s.toString())
                    search_address_recycler_view.visibility = View.VISIBLE
                } else {
                    search_address_recycler_view.visibility = View.INVISIBLE
                }
            }
        })
    }
    // 검색 통신
    private fun getSearchResult(context: Context, searchString: String) {
        getHomeSearchResponse = networkService.getHomeSearch(searchString)
        getHomeSearchResponse.enqueue(object : Callback<GetHomeSearchResponse> {
            override fun onFailure(call: Call<GetHomeSearchResponse>, t: Throwable) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetHomeSearchResponse>, response: Response<GetHomeSearchResponse>) {
                if (response.isSuccessful)
                    if (response.body()!!.status == 200) {
                        getHomeSearchResponseData = response.body()!!.data

                        searchLocationListAdapter = SearchLocationListAdapter(context, dataList)

                        Log.v("검색결과", getHomeSearchResponseData.toString())

                        // 카페 탭
                        val cafeList = ArrayList<GetHomeSearchResponseData>()

                        for (value in getHomeSearchResponseData) {
                            if (!value.type) {
                                cafeList.add(value)
                            }

                            searchLocationListAdapter = SearchLocationListAdapter(context, cafeList)
                            searchLocationListAdapter.setOnItemClickListener(View.OnClickListener { v ->
                                Log.d("asdfg", "clicked")
                                val idx: Int = search_address_recycler_view.getChildAdapterPosition(v!!) // 선택된 자식뷰
                                for (value in 0 until cafeList.size) {
                                    cafeList[value].dot = false
                                }
                                cafeList[idx].dot = true
                                cafe_name = cafeList[idx].cafe_name
                                cafe_id = cafeList[idx].cafe_id
                                cafe_address= cafeList[idx].cafe_address_detail
                                searchLocationListAdapter.notifyDataSetChanged()
                            })
                            search_address_recycler_view.adapter = searchLocationListAdapter
                            search_address_recycler_view.layoutManager = LinearLayoutManager(context)
                        }

                    } else if (response!!.body()!!.status == 204) {
                        toast("인기 카페가 존재하지 않습니다")
                    }
            }
        })
    }

    fun setOnClickListener(){
        img_addreview_location_complete.setOnClickListener {
            if(cafe_name != null){
                Log.v("리뷰서치액티비티(cafe_name) ", cafe_name)
                val intent= Intent(this,WriteReviewActivity::class.java)
                intent.putExtra("cafe_name",cafe_name)
                intent.putExtra("cafe_id",cafe_id)
                intent.putExtra("cafe_address",cafe_address)
                setResult(RESULT_OK,intent)
            finish()
            }else toast("카페 장소를 설정해 주세요")
        }
        img_search_location_back.setOnClickListener {
            finish()
        }

    }
}