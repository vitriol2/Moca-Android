package com.example.parkseeun.moca_android.model.get

data class GetHomeHotplaceResponse(
    val status : Int,
    val message : String,
    val data : ArrayList<HomeHotplaceData>
)

data class HomeHotplaceData(
    val hot_place_id : Int,
    val hot_place_name : String,
    val hot_place_img : String
)