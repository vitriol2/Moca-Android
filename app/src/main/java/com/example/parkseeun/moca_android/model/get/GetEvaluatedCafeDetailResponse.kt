package com.example.parkseeun.moca_android.model.get

/**
 * Created by choisunpil on 10/01/2019.
 */

data class GetEvaluatedCafeDetailResponse(
        var status: Int,
        var message: String,
        var data:EvaluatedCafeDetail
)
data class EvaluatedCafeDetail(
        var cafe_name:String,
        var cafe_address_detail:String,
        var evaluated_cafe_total_evaluation : String,
        var evaluated_cafe_rating :Int
)