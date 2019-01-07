package com.example.parkseeun.moca_android.ui.mypage.coupon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetMembershipResponse
import com.example.parkseeun.moca_android.model.get.GetMembershipResponseData
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.mypage.coupon.recyclerview.CouponHistoryViewAdapter
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_history.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HistoryActivity : AppCompatActivity() {
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var getMembershipResponse : Call<GetMembershipResponse>
    lateinit var couponViewAdapter: CouponHistoryViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        history_back_iv.setOnClickListener { finish() }
        communicate()
    }
    private fun communicate() {
        getMembershipResponse = networkService.getMembership(User.token)
        getMembershipResponse.enqueue(object : Callback<GetMembershipResponse>{
            override fun onFailure(call: Call<GetMembershipResponse>?, t: Throwable?) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetMembershipResponse>?, response: Response<GetMembershipResponse>?) {
                if(response!!.isSuccessful)
                    if (response!!.body()!!.status == 200) {
                        var dataList: ArrayList<GetMembershipResponseData> = response.body()!!.data
                        dataList.reverse()
                        couponViewAdapter = CouponHistoryViewAdapter(applicationContext!!, dataList)
                        history_coupon_rv.adapter = couponViewAdapter
                        history_coupon_rv.layoutManager = LinearLayoutManager(applicationContext)
                    } else if (response!!.body()!!.status != 404) {
                        toast(response!!.body()!!.status.toString() + ": " + response!!.body()!!.message)
                    }
            }
        })
    }
}
