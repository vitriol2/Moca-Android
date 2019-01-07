package com.example.parkseeun.moca_android.model.post

data class PostJoinData(
    val user_id : String,
    val user_password : String,
    val user_name : String,
    val user_phone : String,
    val user_img : String? // null일 수 있으면 ? 붙이기
)