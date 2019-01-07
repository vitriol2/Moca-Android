package com.example.parkseeun.moca_android.ui.location.data

data class LocationMainData(
    var cafe_id: Int,
    var cafeImageUrl: String?,
    var name: String,
    var address: String,
    var selected: Boolean = false
)

