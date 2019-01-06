package com.example.parkseeun.moca_android.model.get

data class GetCafeListResponse (
        val status: Int,
        val message: String?,
        val data: ArrayList<GetCafeListResponseData>
)

data class GetCafeListResponseData(
        val cafe_id: Int,
        val cafe_name: String,
        val cafe_address_detail: String,
        val cafe_rating_avg: Int,
        val cafe_menu_img_url: String?,
        val cafe_main_menu_name: String,
        val cafe_concept_name: String
)