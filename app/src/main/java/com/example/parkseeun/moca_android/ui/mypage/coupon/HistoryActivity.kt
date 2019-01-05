package com.example.parkseeun.moca_android.ui.mypage.coupon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.mypage.coupon.recyclerview.CouponHistoryData
import com.example.parkseeun.moca_android.ui.mypage.coupon.recyclerview.CouponHistoryViewAdapter
import kotlinx.android.synthetic.main.activity_history.*
import java.util.*

class HistoryActivity : AppCompatActivity() {
    lateinit var couponViewAdapter: CouponHistoryViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        history_back_iv.setOnClickListener { finish() }
        setRecyclerView()
    }
    private fun setRecyclerView() {
        // 임시 데이터
        var dataList : ArrayList<CouponHistoryData> = ArrayList()
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", -1))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 0))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 0))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 0))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 0))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 0))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 0))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 0))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 0))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 0))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 0))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 1))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", -1))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 0))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 0))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 0))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 0))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 0))
        dataList.add(CouponHistoryData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아이스베어 홈", "2019.12.22", 0))

        dataList.reverse()
        couponViewAdapter = CouponHistoryViewAdapter(applicationContext!!, dataList)
        history_coupon_rv.adapter = couponViewAdapter
        history_coupon_rv.layoutManager = LinearLayoutManager(applicationContext)
    }
}
