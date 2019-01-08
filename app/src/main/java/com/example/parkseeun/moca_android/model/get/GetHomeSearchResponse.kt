package com.example.parkseeun.moca_android.model.get

data class GetHomeSearchResponse(
    val status : Int,
    val message : String,
    val data : ArrayList<GetHomeSearchResponseData>
)

data class GetHomeSearchResponseData(
    val cafe_id : Int,
    val cafe_name : String,
    val cafe_img_url : String,
    val cafe_address_detail : String,
    val type : Boolean
)