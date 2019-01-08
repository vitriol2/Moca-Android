package com.example.parkseeun.moca_android.model.get

data class GetMocaRecommendHotPlaceResponse (
    val status : Int,
    val message : String,
    val data : ArrayList<GetMocaRecommendHotPlaceData>
)

data class GetMocaRecommendHotPlaceData (
    val hot_place_id : Int,
    val hot_place_name : String,
    val hot_place_img : String
)