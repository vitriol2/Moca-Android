package com.example.parkseeun.moca_android.model.get

data class GetMembershipResponse (
        val status: Int,
        val message: String?,
        val data: ArrayList<GetMembershipResponseData>
)

data class GetMembershipResponseData(
        val cafe_id: Int,
        val cafe_name: String,
        val membership_create_date: String,
        val cafe_img_url: String?
)