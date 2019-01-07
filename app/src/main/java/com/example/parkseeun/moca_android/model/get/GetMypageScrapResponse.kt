package com.example.parkseeun.moca_android.model.get

data class GetMypageScrapResponse(
    val status : String,
    val message : String,
    val data : ArrayList<CafeData>
)

data class CafeData(
    val cafe_id : Int,
    val cafe_name : String,
    val address_district_name : String,
    val cafe_rating_avg : Int,
    val cafe_img_url : ArrayList<String>
)