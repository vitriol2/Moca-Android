package com.example.parkseeun.moca_android.model.get

data class GetFollowerResponseData (
        val user_id: String,
        val user_name: String,
        val user_phone: String?,
        val user_img_url: String,
        val user_status_comment: String?,
        val auth: Boolean,
        val follow: Boolean
)