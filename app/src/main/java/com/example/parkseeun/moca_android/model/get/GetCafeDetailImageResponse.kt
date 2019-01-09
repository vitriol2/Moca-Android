package com.example.parkseeun.moca_android.model.get

data class GetCafeDetailImageResponse(
    val status : String,
    val message : String,
    val data : ArrayList<CafeDetailImageData>
)

data class CafeDetailImageData(
    val cafe_img_url : String,
    val cafe_img_main: Boolean
)