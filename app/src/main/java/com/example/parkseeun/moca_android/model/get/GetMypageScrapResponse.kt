package com.example.parkseeun.moca_android.model.get

data class GetMypageScrapResponse(
    val status : Int,
    val message : String,
    val data : ArrayList<ScrapCafeData>
)

data class ScrapCafeData(
    val cafe_id : Int,
    val cafe_name : String,
    val address_district_name : String,
    val cafe_address_detail : String,
    val cafe_rating_avg : Int,
    val cafe_img_url : ArrayList<ScrapCafeDataImage>
)
data class ScrapCafeDataImage(
    val cafe_img_url: String?
)
