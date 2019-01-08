package com.example.parkseeun.moca_android.ui.location.data

data class LocationCafeDetailData(
    var cafe_id: Int,
    var cafe_img_url: String?,
    val cafe_latitude : Double,
    val cafe_longitude : Double,
    var name: String,
    var address: String,
    var cafe_rating_avg: Int,
    val distance : String,
    var selected: Boolean = false
)

