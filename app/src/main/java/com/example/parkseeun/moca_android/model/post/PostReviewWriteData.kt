package com.example.parkseeun.moca_android.model.post

data class PostReviewWriteData (
    val cafe_id : Int,
    val title : String,
    val content : String,
    val rating : Int,
    val image : ArrayList<String>
)
