package com.example.parkseeun.moca_android.ui.mocapicks

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetBaristaEvaluationResponse
import com.example.parkseeun.moca_android.model.get.GetEvaluatedCafeDetailResponse
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.util.User
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_moca_picks_barista.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MocaPicksBaristaActivity : AppCompatActivity() {

    private var cafe_id : Int  = 0
    private var barista_id: Int =0
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var getBaristaEvaluationResponse: Call<GetBaristaEvaluationResponse>
    private lateinit var civ_barista_profile:CircleImageView
    private lateinit var tv_barista_baristaName:TextView
    private lateinit var tv_barista_baristaGrade:TextView
    private lateinit var tv_barista_baristaSpec:TextView
    private lateinit var rating_barista_evaluateItem1 :RatingBar
    private lateinit var tv_barista_evaluateItem1:TextView
    private lateinit var rating_barista_evaluateItem2 :RatingBar
    private lateinit var tv_barista_evaluateItem2:TextView
    private lateinit var rating_barista_evaluateItem3 :RatingBar
    private lateinit var tv_barista_evaluateItem3:TextView
    private lateinit var rating_barista_evaluateItem4 :RatingBar
    private lateinit var tv_barista_evaluateItem4:TextView
    private lateinit var rating_barista_evaluateItem5 :RatingBar
    private lateinit var tv_barista_evaluateItem5:TextView
    private lateinit var tv_barista_evaluation_summary:TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moca_picks_barista)
        var intent :Intent  = getIntent()
        cafe_id = intent.getIntExtra("cafe_id",0)
        barista_id = intent.getIntExtra("barista_id",0)
        civ_barista_profile = findViewById(R.id.civ_barista_profile)
        tv_barista_baristaName  = findViewById(R.id.tv_barista_baristaName)
        tv_barista_baristaGrade = findViewById(R.id.tv_barista_baristaGrade)
        tv_barista_baristaSpec = findViewById(R.id.tv_barista_baristaSpec)
        rating_barista_evaluateItem1 = findViewById(R.id.rating_barista_evaluateItem1)
        tv_barista_evaluateItem1 = findViewById(R.id.tv_barista_evaluateItem1)
        rating_barista_evaluateItem2 = findViewById(R.id.rating_barista_evaluateItem2)
        tv_barista_evaluateItem2 =  findViewById(R.id.tv_barista_evaluateItem2)
        rating_barista_evaluateItem3 =  findViewById(R.id.rating_barista_evaluateItem3)
        tv_barista_evaluateItem3 = findViewById(R.id.tv_barista_evaluateItem3)
        rating_barista_evaluateItem4 =  findViewById(R.id.rating_barista_evaluateItem4)
        tv_barista_evaluateItem4 = findViewById(R.id.tv_barista_evaluateItem4)
        rating_barista_evaluateItem5 = findViewById(R.id.rating_barista_evaluateItem5)
        tv_barista_evaluateItem5 =  findViewById(R.id.tv_barista_evaluateItem5)
        tv_barista_evaluation_summary  = findViewById(R.id.tv_barista_evaluation_summary)
        getBaristaEvaluation()
        ib_moca_picks_back_btn.setOnClickListener {
            finish()
        }

    }


    private fun getBaristaEvaluation() {
        getBaristaEvaluationResponse  = networkService.getBaristaEvaluation(User.token!!, cafe_id!!,barista_id!!)
        getBaristaEvaluationResponse.enqueue(object: Callback<GetBaristaEvaluationResponse> {
            override fun onFailure(call: Call<GetBaristaEvaluationResponse>, t: Throwable) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetBaristaEvaluationResponse>, response: Response<GetBaristaEvaluationResponse>) {
                if(response.isSuccessful) {
                    if(response.body()!!.status==200) {
                        /*********/
                        Glide.with(applicationContext).load(response.body()!!.data.barista_img_url).into(civ_barista_profile)
                        tv_barista_baristaName!!.text = response.body()!!.data.barista_name
                        tv_barista_baristaSpec!!.text = response.body()!!.data.barista_title
                        tv_barista_baristaGrade!!.text = ((response.body()!!.data.evaluation_bean_condition +
                                                           response.body()!!.data.evaluation_roasting +
                                                           response.body()!!.data.evaluation_creativity +
                                                           response.body()!!.data.evaluation_reasonable +
                                                           response.body()!!.data.evaluation_consistancy)/5).toString()
                        rating_barista_evaluateItem1!!.rating = response.body()!!.data.evaluation_bean_condition.toFloat()
                        tv_barista_evaluateItem1!!.text =response.body()!!.data.evaluation_bean_condition_comment
                        rating_barista_evaluateItem2!!.rating = response.body()!!.data.evaluation_roasting.toFloat()
                        tv_barista_evaluateItem2!!.text = response.body()!!.data.evaluation_roasting_comment
                        rating_barista_evaluateItem3!!.rating = response.body()!!.data.evaluation_creativity.toFloat()
                        tv_barista_evaluateItem3!!.text = response.body()!!.data.evaluation_creativity_comment
                        rating_barista_evaluateItem4!!.rating = response.body()!!.data.evaluation_reasonable.toFloat()
                        tv_barista_evaluateItem4!!.text = response.body()!!.data.evaluation_reasonable_comment
                        rating_barista_evaluateItem5!!.rating = response.body()!!.data.evaluation_consistancy.toFloat()
                        tv_barista_evaluateItem5!!.text = response.body()!!.data.evaluation_reasonable_comment
                        tv_barista_evaluation_summary!!.text = response.body()!!.data.evaluation_summary



                    }
                    else {
                        toast(response.body()!!.status.toString() + " : " + response.body()!!.message)
                    }
                }
            }
        })
    }







}
