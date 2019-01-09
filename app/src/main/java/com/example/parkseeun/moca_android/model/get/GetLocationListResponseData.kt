package com.example.parkseeun.moca_android.model.get


data class GetLocationListResponseData(

    var address_name: String?,
    var road_address_name: String?,
    var category_group_code: String?,
    var category_group_name: String?,
    var category_name: String?,
    var distance: String?,
    var id: String?,
    var phone: String?,
    var place_name: String?,
    var place_url: String?,
    var x: String?,
    var y: String?,
    var dot: Boolean? = false

)
