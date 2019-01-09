package com.example.parkseeun.moca_android.model.get

data class GetHotPlaceListResponse (
    val status : Int,
    val message : String,
    val data : ArrayList<GetHotPlaceListData>
)

data class GetHotPlaceListData(
    val cafe_id : Int,
    val cafe_name : String,
    val cafe_subway : String,
    val cafe_rating_avg : Int,
    val evaluated_cafe_is : Boolean
)