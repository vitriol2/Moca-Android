package com.example.parkseeun.moca_android.model.get

data class GetMocaPicksDetailResponse (
    val status : Int,
    val message : String,
    val data : GetMocaPicksDetailData
)

data class GetMocaPicksDetailData(
    val cafe_id : Int,
    val cafe_name : String,
    val evaluated_cafe_total_evaluation : String,
    val evaluated_cafe_rating : Int
)