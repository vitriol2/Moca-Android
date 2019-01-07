package com.example.parkseeun.moca_android.model.get

data class GetMocaplusResponse(
    val status : String,
    val messge : String,
    val data : ArrayList<MocaplusData>
)

data class MocaplusData(
    val plus_subject_id : Int,
    val editor_id : String,
    val editor_name : String,
    val editor_img_url : String,
    val plus_subject_title : String,
    val plus_subject_img_url : String
)