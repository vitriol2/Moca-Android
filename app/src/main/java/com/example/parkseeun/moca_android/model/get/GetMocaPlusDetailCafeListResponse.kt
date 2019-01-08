package com.example.parkseeun.moca_android.model.get

data class GetMocaPlusDetailCafeListResponse (
    val status : Int,
    val message : String,
    val data : ArrayList<GetMocaPlusDetailCafeListData>
)

data class GetMocaPlusDetailCafeListData (
    val plus_contents_id : Int,
    val plus_subject_id : Int,
    val cafe_id : Int,
    val cafe_name : String,
    val address_district_name : String,
    val plus_contents_content : String,
    val contentImages : ArrayList<GetMocaContentImagesData>
)

data class GetMocaContentImagesData (
    val plus_default_img_url : String
)