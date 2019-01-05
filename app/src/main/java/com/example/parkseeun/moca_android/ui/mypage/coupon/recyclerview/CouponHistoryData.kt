package com.example.parkseeun.moca_android.ui.mypage.coupon.recyclerview


data class CouponHistoryData(
        val img: String,
        val name: String,
        val date: String,
        var flag: Int // -1:bottom, 0:plain, 1:top
)