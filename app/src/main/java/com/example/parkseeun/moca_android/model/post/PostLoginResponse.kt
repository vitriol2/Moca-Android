package com.example.parkseeun.moca_android.model.post

data class PostLoginResponse (
        val status: Int,
        val data: PostLoginResponseData
)

data class PostLoginResponseData(
        val token: String?
)