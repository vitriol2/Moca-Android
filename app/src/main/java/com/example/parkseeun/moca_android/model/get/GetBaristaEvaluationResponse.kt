package com.example.parkseeun.moca_android.model.get

/**
 * Created by choisunpil on 11/01/2019.
 */

data  class GetBaristaEvaluationResponse(

        var status:Int,
        var message:String,
        var data:GetBaristaEvaluation
)




data  class GetBaristaEvaluation (
        var barista_id:Int,
        var barista_name:String,
        var barista_title :String,
        var barista_img_url : String,
        var evaluation_bean_condition:Int,
        var evaluation_bean_condition_comment : String,
        var evaluation_roasting:Int,
        var evaluation_roasting_comment : String,
        var evaluation_creativity:Int,
        var evaluation_creativity_comment : String,
        var evaluation_reasonable:Int,
        var evaluation_reasonable_comment:String,
        var evaluation_consistancy:Int,
        var evaluation_consistancy_comment:String,
        var evaluation_summary : String
)
