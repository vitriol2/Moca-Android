package com.example.parkseeun.moca_android.ui.detail.nearbyList

import android.app.Application
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.post.PostNearByCafeData
import com.example.parkseeun.moca_android.model.post.PostNearByCafeResponse
import com.example.parkseeun.moca_android.model.post.PostNearByCafeResponseData
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.detail.detailReviewAll.ReviewAllPopularData
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_nearby_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NearbyListActivity : AppCompatActivity() {

    private val TAG = "NearbyListActivity"

    private var cafe_id: Int = 0
    private var latitude: Double = 0.toDouble()
    private var longitude: Double = 0.toDouble()



    private val nearbyList = ArrayList<NearbyListData>()

    val networkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_list)

        cafe_id = intent.getIntExtra("cafe_id", 0)
        latitude = intent.getDoubleExtra("cafe_latitude", 0.0)
        longitude = intent.getDoubleExtra("cafe_longitude", 0.0)

        Log.v("latitude", latitude.toString())

        setOnBtnClickListeners()

        postCafeNearbyResponse(latitude.toString(), longitude.toString(), cafe_id, 1)

    }

    private fun setOnBtnClickListeners() {
        iv_act_nearby_list_back.setOnClickListener {
            finish()
        }
    }
/*
    private fun setRecyclerView() {
        rv_act_nearby_list.layoutManager = LinearLayoutManager(this)
        rv_act_nearby_list.adapter = NearbyListAdapter(this, nearbyList)
    }
    */


    private fun postCafeNearbyResponse(latitude : String, longitude : String, cafe_id : Int, is_cafe_detail : Int) {
        val nearbyData: PostNearByCafeData = PostNearByCafeData(latitude, longitude, cafe_id, is_cafe_detail)

        Log.v(TAG, "latitude" + latitude)
        val postCafeNearbyResponse = networkService.postCafeNearbyResponse(User.token, nearbyData)

        postCafeNearbyResponse.enqueue(object : Callback<PostNearByCafeResponse> {
            override fun onFailure(call: Call<PostNearByCafeResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<PostNearByCafeResponse>, response: Response<PostNearByCafeResponse>) {
                if (response.isSuccessful) {
                    Log.v("nearbyResponse", "load success123")

                    if (response.body()!!.data != null) {
                        val temp: ArrayList<PostNearByCafeResponseData> = response.body()!!.data
                        if (temp != null) {
                            if (temp.size > 0) {
                                Log.v("nearbydata", temp.size.toString())

                                rv_act_nearby_list.layoutManager = LinearLayoutManager(this@NearbyListActivity)
                                rv_act_nearby_list.adapter = NearbyListAdapter(this@NearbyListActivity, temp)



                            }
                        }
                    }
                }
            }
        })
    }

}
