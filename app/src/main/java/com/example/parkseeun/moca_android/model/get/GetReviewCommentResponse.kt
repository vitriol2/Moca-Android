package com.example.parkseeun.moca_android.model.get

data class GetReviewCommentResponse (
        val status: Int,
        val message: String,
        val data: ArrayList<GetReviewCommentResponseData>
)

data class GetReviewCommentResponseData (
        val review_comment_id: Int,
        val user_id: String,
        val user_name: String,
        val user_img_url: String?,
        val review_comment_content: String?,
        val review_comment_date: String,
        val time: String
)