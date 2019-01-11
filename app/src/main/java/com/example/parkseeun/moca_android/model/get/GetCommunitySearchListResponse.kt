package com.example.parkseeun.moca_android.model.get


data class GetCommunitySearchListResponse (
    val status : Int,
    val message : String,
    val data : CommunitySearchData
)

data class CommunitySearchData (
    val popularReviewList: ArrayList<ReviewData>,
    val reviewListOrderByLatest : ArrayList<ReviewData>,
    val searchUserList : ArrayList<SearchUserData>
)

data class ReviewData(
    val review_id : Int,
    val review_img_url : String
)

data class SearchUserData(
    val user_id : String,
    val user_name : String,
    val user_status_comment : String,
    val user_img_url : String,
    var follow_is : Boolean
)



