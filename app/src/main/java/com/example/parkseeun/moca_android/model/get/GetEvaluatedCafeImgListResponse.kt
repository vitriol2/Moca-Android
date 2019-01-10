package com.example.parkseeun.moca_android.model.get

/**
 * Created by choisunpil on 10/01/2019.
 */
data class GetEvaluatedCafeImgListResponse(
        var status: Int,
        var message: String,
        var data:ArrayList<EvaluatedCafeImg>

)

data class EvaluatedCafeImg(
        var evaluated_cafe_img_url : String,
        var evaluated_cafe_main_img :  Boolean
)