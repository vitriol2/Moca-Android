package com.example.parkseeun.moca_android.ui.community.review_write

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetLocationListResponse
import com.example.parkseeun.moca_android.model.get.GetLocationListResponseData
import com.example.parkseeun.moca_android.network.NetworkService
import com.example.parkseeun.moca_android.ui.community.feed.FeedActivity
import com.example.parkseeun.moca_android.ui.community.review_write.data.CafeListData
import com.example.parkseeun.moca_android.ui.community.review_write.adapter.SearchLocationListAdapter
import com.example.parkseeun.moca_android.ui.location.adapter.LocationSearchAdapter
import kotlinx.android.synthetic.main.activity_community_search_address.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ReviewSearchLocationActivity : AppCompatActivity() {
    lateinit var networkService : NetworkService
    lateinit var locationSearchItems : ArrayList<GetLocationListResponseData>
    // RecyclerView 설정
    lateinit var searchLocationListAdapter : SearchLocationListAdapter
    lateinit var locationSearchAdapter : LocationSearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_search_address)
       // setRecyclerView()
        setOnClickListener()

        val builder = Retrofit.Builder()
        val retrofit_loc_search = builder
            .baseUrl("https://dapi.kakao.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        networkService = retrofit_loc_search.create(NetworkService::class.java)

        var header = "KakaoAK f58c0b6bf01032faee81071dd1d935c6"
//        var location_keyword :String = arguments.getString("keyword")

        et_addreview_search_address.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != "") {
                    getLocationList(applicationContext, header, s.toString())
                }else {
                    search_address_recycler_view.visibility = View.INVISIBLE
                }
            }
        })





    }

    private fun getLocationList(context : Context, header : String, location_keyword : String) {
        var getLocationList = networkService.getLocationList(header,location_keyword)
        getLocationList.enqueue(object : Callback<GetLocationListResponse> {
            override fun onFailure(call: Call<GetLocationListResponse>?, t: Throwable?) {
                Log.v("failFail", t!!.message.toString())
            }

            override fun onResponse(call: Call<GetLocationListResponse>?, response: Response<GetLocationListResponse>?) {
                if(response!!.isSuccessful){

                    locationSearchItems = response.body()!!.documents

                    Log.v("locationLocation", locationSearchItems.toString())

                    locationSearchAdapter = LocationSearchAdapter(context,locationSearchItems)

                    search_address_recycler_view.adapter = locationSearchAdapter
                    search_address_recycler_view.layoutManager = LinearLayoutManager(context)
                    //  locationSearchAdapter.setOnItemClickListener(this@LocationSearchResult)

                }

                Log.v("onTextChanged 제발","${response.raw()}")
            }

        })
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

        searchLocationListAdapter = SearchLocationListAdapter(this, dataList)
        search_address_recycler_view.adapter = searchLocationListAdapter
        search_address_recycler_view.layoutManager = LinearLayoutManager(applicationContext)
    }

    fun setOnClickListener(){
        img_addreview_location_complete.setOnClickListener {
            finish()
        }
        img_search_location_back.setOnClickListener {
            finish()
        }

    }
}