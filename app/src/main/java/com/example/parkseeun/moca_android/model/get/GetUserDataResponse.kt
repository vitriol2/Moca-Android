package com.example.parkseeun.moca_android.model.get

data class GetUserDataResponse (
        val status: Int,
        val message: String,
        val data: GetUserDataResponseData
)

data class GetUserDataResponseData(
        val user_id: String,
        val user_name: String,
        val user_img_url: String?,
        val user_status_comment: String?,
        val review_count: Int,
        val follower_count: Int,
        val following_count: Int,
        val auth: Boolean,
        val follow: Boolean
)