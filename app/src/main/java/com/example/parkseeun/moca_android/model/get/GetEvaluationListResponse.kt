package com.example.parkseeun.moca_android.model.get

/**
 * Created by choisunpil on 10/01/2019.
 */
data class GetEvaluationListResponse(
        var status : Int ,
        var message : String,
        var data : ArrayList<Evaluation>
)
data class Evaluation(
        var barista_id:Int,
        var barista_name:String,
        var barista_title:String,
        var barista_img_url:String,
        var evaluation_bean_condition:Int,
        var evaluation_bean_condition_comment:String,
        var evaluation_roasting :Int,
        var evaluation_roasting_comment:String,
        var evaluation_creativity:Int,
        var evaluation_creativity_comment:String,
        var evaluation_reasonable:Int,
        var evaluation_reasonable_comment:String,
        var evaluation_consistancy:Int,
        var evaluation_consistancy_comment:String
)