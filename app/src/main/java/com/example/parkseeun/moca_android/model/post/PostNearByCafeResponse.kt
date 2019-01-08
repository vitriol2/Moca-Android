package com.example.parkseeun.moca_android.model.post

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class PostNearByCafeResponse(
        val status : Int,
        val message : String,
        var data : ArrayList<PostNearByCafeResponseData>
)

data class PostNearByCafeResponseData (
        val cafe_id : Int,
        val cafe_latitude : Double,
        val cafe_longitude : Double,
        val cafe_name : String,
        val cafe_img_url : String,
        val address_district_name : String,
        val cafe_rating_avg : Int,
        val distance : String
)