package com.example.parkseeun.moca_android.model.get

data class GetRankingResponse (
        val status : Int,
        val message : String,
        val data : ArrayList<GetRankingResponseData>
)

data class GetRankingResponseData(
        val cafe_id: Int,
        val cafe_name: String,
        val cafe_menu_img_url: String?,
        val address_district_name: String,
        val cafe_rating_avg: Int,
        val evaluated_cafe_is: Boolean
)