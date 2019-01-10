package com.example.parkseeun.moca_android.model.get

data class GetMypageCouponResponse(
    val status : Int,
    val message : String,
    val data : ArrayList<CouponData>
)

data class CouponData(
    val coupon_id : Int,
    val coupon_authentication_number : String,
    val coupon_create_date : String
)