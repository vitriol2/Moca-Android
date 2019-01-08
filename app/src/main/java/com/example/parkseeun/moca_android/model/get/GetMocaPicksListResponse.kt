package com.example.parkseeun.moca_android.model.get

data class GetMocaPicksListResponse (
    val status : Int,
    val message : String,
    val data : ArrayList<GetMocaPicksListData>
)

data class GetMocaPicksListData (
    val cafe_id : Int,
    val cafe_name : String,
    val cafe_introduction : String,
    val cafe_rating : Int,
    val latitude : Float,
    val longitude : Float,
    val cafe_img_main : String,
    val nerby_subway : String
)