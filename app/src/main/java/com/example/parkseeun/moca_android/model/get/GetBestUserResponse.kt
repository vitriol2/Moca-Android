package com.example.parkseeun.moca_android.model.get

data class GetBestUserResponse (
    val status : Int,
    val message : String,
    val data : ArrayList<GetBestUserData>
)

data class GetBestUserData(
    val user_id : String,
    val user_name : String,
    val user_phone : String,
    val user_img_url : String,
    val user_status_comment : String,
    val auth : Boolean,
    val follow : Boolean
)