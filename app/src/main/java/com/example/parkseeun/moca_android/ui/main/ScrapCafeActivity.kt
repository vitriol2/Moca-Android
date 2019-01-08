package com.example.parkseeun.moca_android.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetMypageScrapResponse
import com.example.parkseeun.moca_android.model.get.ScrapCafeData
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_scrap_cafe.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScrapCafeActivity : AppCompatActivity() {
    private val TAG = "ScrapCafeActivity"
    val scrapList = ArrayList<ScrapCafeData>()

    lateinit var scrapCafeAdapter: ScrapCafeAdapter

    val networkService by lazy {
        ApplicationController.instance.networkService
    }

    val dataList = ArrayList<ScrapCafeData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrap_cafe)


        setNetwork()

        setRecyclerView()

        setBtnOnClickListeners()


    }

    private fun setNetwork() {
        getScrapCafeResponse()
    }

    private fun setRecyclerView() {
        scrapCafeAdapter = ScrapCafeAdapter(this, scrapList)
        rv_act_scrap_cafe.layoutManager = LinearLayoutManager(this)
        rv_act_scrap_cafe.adapter = scrapCafeAdapter

    }

    private fun setBtnOnClickListeners() {
        iv_act_scrap_cafe_back.setOnClickListener {
            finish()
        }
    }

    private fun getScrapCafeResponse() {
        val getScrapCafeResponse = networkService.getMypageScrapResponse(User.token!!)

        getScrapCafeResponse.enqueue(object : Callback<GetMypageScrapResponse> {
            override fun onFailure(call: Call<GetMypageScrapResponse>, t: Throwable) {
                Log.e(TAG, "load failed")
            }

            override fun onResponse(call: Call<GetMypageScrapResponse>, response: Response<GetMypageScrapResponse>) {
                if (response.isSuccessful) {
                    Log.v(TAG, "load success")
                    val temp: ArrayList<ScrapCafeData> = response.body()!!.data
                    if (temp != null)
                        if (temp.size > 0) {
                            Log.v(TAG, temp.size.toString())
                            val position = scrapCafeAdapter.itemCount
                            scrapCafeAdapter.dataList.addAll(temp)
                            scrapCafeAdapter.notifyItemInserted(position)
                        }
                }
            }
        })

    }


}