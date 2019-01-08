package com.example.parkseeun.moca_android.model.post

data class PostNearByCafeData(
    val cafe_latitude : String,
    val cafe_longitude : String,
    val cafe_id : Int?,
    val is_cafe_detail : Int?
)