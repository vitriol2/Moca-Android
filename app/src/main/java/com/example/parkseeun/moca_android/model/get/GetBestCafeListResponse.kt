package com.example.parkseeun.moca_android.model.get

data class GetBestCafeListResponse (
    val status : Int,
    val message : String,
    val data : ArrayList<GetBestCafeListData>
)

data class GetBestCafeListData (
    val cafe_id : Int,
    val cafe_name : String,
    val cafe_img_url : String,
    val scrap_count : Int,
    val review_count : Int
)