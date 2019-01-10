package com.example.parkseeun.moca_android.model.get

data class GetMypageMembershipResponse(
    val status : Int,
    val message : String,
    val data : ArrayList<MembershipData>
)

data class MembershipData (
    val cafe_id : Int,
    val membership_create_date : String,
    val cafe_img_url : String
)