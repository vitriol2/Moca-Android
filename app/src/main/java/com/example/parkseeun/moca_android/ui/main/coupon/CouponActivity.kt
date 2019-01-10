package com.example.parkseeun.moca_android.ui.main.coupon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.CouponData
import com.example.parkseeun.moca_android.model.get.GetMypageCouponResponse
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_coupon.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponActivity : AppCompatActivity() {
    val TAG = "CouponActivity"

    private val dataList = ArrayList<String>()
    private var couponList = ArrayList<CouponData>()

    val networkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon)

        setNetwork()

        setOnBtnClickListeners()

    }

    private fun setNetwork() {
        getMypageCouponResponse()
    }

    private fun getMypageCouponResponse() {
        val getMypageCouponResponse = networkService.getMypageCouponResponse(User.token)

        getMypageCouponResponse.enqueue(object : Callback<GetMypageCouponResponse> {
            override fun onFailure(call: Call<GetMypageCouponResponse>, t: Throwable) {
                Log.e(TAG, "load failed")
            }

            override fun onResponse(call: Call<GetMypageCouponResponse>, response: Response<GetMypageCouponResponse>) {
                if (response!!.isSuccessful)
                    if (response.body()!!.status == 200) {
                        couponList = response.body()!!.data
                        setRecyclerView()
                    }
            }
        })
    }

    private fun setRecyclerView() {
        rv_act_coupon.layoutManager = LinearLayoutManager(this)
        rv_act_coupon.adapter = CouponAdapter(this, couponList)
    }

    private fun setOnBtnClickListeners() {
        iv_act_coupon_back.setOnClickListener {
            finish()
        }
    }
}
