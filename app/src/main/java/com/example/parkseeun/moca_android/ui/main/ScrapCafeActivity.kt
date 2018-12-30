package com.example.parkseeun.moca_android.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_scrap_cafe.*

class ScrapCafeActivity : AppCompatActivity() {

    val dataList = ArrayList<ScrapCafeData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrap_cafe)

        makeList()


        setRecyclerView()

        setBtnOnClickListeners()

    }

    private fun makeList() {
        for(i in 1..15) {
            dataList.add(ScrapCafeData("카페 $i", "위치 $i"))
        }
    }

    private fun setRecyclerView() {
        rv_act_scrap_cafe.layoutManager = LinearLayoutManager(this)
        rv_act_scrap_cafe.adapter = ScrapCafeAdapter(this, dataList)

    }

    private fun setBtnOnClickListeners() {
        iv_act_scrap_cafe_back.setOnClickListener {
            finish()
        }
    }

}
