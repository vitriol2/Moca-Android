package com.example.parkseeun.moca_android.model.get

data class GetCafeSignitureResponse(
    val status : String,
    val message : String,
    val data : ArrayList<SignitureData>
)
data class SignitureData(
    val cafe_signiture_menu : String,
    val cafe_signiture_price : String,
    val cafe_signiture_img : String
)