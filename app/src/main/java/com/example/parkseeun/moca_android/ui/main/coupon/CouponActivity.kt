package com.example.parkseeun.moca_android.ui.main.coupon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_coupon.*

class CouponActivity : AppCompatActivity() {

    private val dataList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon)

        makeList()

        setRecyclerView()

        setOnBtnClickListeners()
    }

    private fun setRecyclerView() {
        rv_act_coupon.layoutManager = LinearLayoutManager(this)
        rv_act_coupon.adapter = CouponAdapter(this, dataList)
    }

    private fun makeList() {
        for(i in 1..15) {
            dataList.add("$i$i$i$i.$i$i.$i$i")
        }
    }

    private fun setOnBtnClickListeners() {
        iv_act_coupon_back.setOnClickListener {
            finish()
        }


    }
}
