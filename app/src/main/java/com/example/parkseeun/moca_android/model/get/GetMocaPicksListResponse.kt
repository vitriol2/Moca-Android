package com.example.parkseeun.moca_android.model.get

data class GetMocaPicksListResponse (
    val status : Int,
    val message : String,
    val data : ArrayList<GetMocaPicksListData>
)


data class GetMocaPicksListData (
var cafe_id: Int,
 var cafe_name:String,
 var evaluated_cafe_rating: Int,
 var address_district_name: String,
var evaluated_cafe_img_url: String,
 var scrab_is: Boolean
)