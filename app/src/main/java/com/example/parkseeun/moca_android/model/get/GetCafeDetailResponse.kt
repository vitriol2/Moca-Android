package com.example.parkseeun.moca_android.model.get

data class GetCafeDetailResponse(
    val status: Int,
    val message: String,
    val data: CafeDetailData
)

data class CafeDetailData(
    val cafe_id: Int,
    val cafe_name: String,
    val cafe_latitude: Double,
    val cafe_longitude: Double,
    val cafe_phone: String,
    val cafe_menu_img_url: String,
    val address_distrival: String,
    val cafe_address_detail: String,
    val cafe_rating_avg: Int,
    val cafe_times: String,
    val cafe_days: String,
    val cafe_option_parking: Boolean,
    val cafe_option_wifi: Boolean,
    val cafe_option_allnight: Boolean,
    val cafe_option_reservation: Boolean,
    val cafe_option_smokingarea: Boolean,
    val cafe_scrab_is: Boolean
)