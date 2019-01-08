package com.example.parkseeun.moca_android.model.get

data class GetFeedResponse (
        val status: Int,
        val message: String,
        val data: ArrayList<GetFeedResponseData>
)