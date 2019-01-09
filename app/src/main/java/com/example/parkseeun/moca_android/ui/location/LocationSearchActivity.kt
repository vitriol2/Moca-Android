package com.example.parkseeun.moca_android.ui.location

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
import com.example.parkseeun.moca_android.ui.location.adapter.LocationSearchAdapter
import kotlinx.android.synthetic.main.activity_location_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationSearchActivity : AppCompatActivity() ,View.OnClickListener {
    lateinit var networkService: NetworkService
    lateinit var locationSearchItems: ArrayList<GetLocationListResponseData>
    // RecyclerView 설정
    lateinit var locationSearchAdapter: LocationSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_search)
        // setRecyclerView()

        val builder = Retrofit.Builder()
        val retrofit_loc_search = builder
            .baseUrl("https://dapi.kakao.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        networkService = retrofit_loc_search.create(NetworkService::class.java)

        var header = "KakaoAK f58c0b6bf01032faee81071dd1d935c6"
//        var location_keyword :String = arguments.getString("keyword")

        et_map_search_address.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != "") {
                    getLocationList(applicationContext, header, s.toString())
                    rv_act_location_search.visibility = View.VISIBLE
                } else {
                    rv_act_location_search.visibility = View.INVISIBLE
                }
            }
        })

        setOnBtnClickListeners()
    }

    override fun onClick(v: View?) {
//        if (rv_act_location_main.indexOfChild(v) != -1) { // 리사이클러뷰의 자식뷰 이면  !
//            val idx: Int = rv_act_location_main.getChildAdapterPosition(v!!) // 선택된 자식뷰
//            cameraToMarker(idx)
//            var markerTitle: String = getCurrentAddress(markerlist[idx].position)
//            floatDialog(idx, markerTitle)
//        }
    }

    private fun getLocationList(context: Context, header: String, location_keyword: String) {
        var getLocationList = networkService.getLocationList(header, location_keyword)
        getLocationList.enqueue(object : Callback<GetLocationListResponse> {
            override fun onFailure(call: Call<GetLocationListResponse>?, t: Throwable?) {
                Log.v("LocationSearchActivity", t!!.message.toString())
            }

            override fun onResponse(
                call: Call<GetLocationListResponse>?,
                response: Response<GetLocationListResponse>?
            ) {
                if (response!!.isSuccessful) {

                    locationSearchItems = response.body()!!.documents

                    Log.v("locationLocation", locationSearchItems.toString())

                    locationSearchAdapter = LocationSearchAdapter(context, locationSearchItems)

                    rv_act_location_search.adapter = locationSearchAdapter
                    rv_act_location_search.layoutManager = LinearLayoutManager(context)
                     // locationSearchAdapter.setOnItemClickListener(this@LocationSearchActivity)

                }

                Log.v("onTextChanged", "${response.raw()}")
            }

        })
    }

    private fun setOnBtnClickListeners() {
        img_location_common_backbtn_black.setOnClickListener {
            finish()
        }
    }
}