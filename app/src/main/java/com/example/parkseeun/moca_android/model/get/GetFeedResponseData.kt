package com.example.parkseeun.moca_android.model.get

data class GetFeedResponseData (
        val review_id: Int,
        val cafe_id: Int,
        val user_id: String,
        val image: ArrayList<GetFeedResponseImage>,
        val review_rating: Int,
        val review_title: String?,
        val review_content: String?,
        val review_date: String,
        val cafe_name: String,
        val cafe_address: String,
        val time: String,
        val like_count: Int,
        val comment_count: Int,
        val auth: Boolean,
        val like: Boolean
)