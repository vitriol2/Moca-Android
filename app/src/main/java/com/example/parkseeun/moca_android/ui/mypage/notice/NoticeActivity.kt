package com.example.parkseeun.moca_android.ui.mypage.notice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_notice.*

class NoticeActivity : AppCompatActivity() {

    private val dataList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        makeData()
        setRecyclerView()

        setOnBtnClickListeners()

    }

    private fun setOnBtnClickListeners() {
        iv_act_notice_back.setOnClickListener {
            finish()
        }
    }

    private fun makeData() {
        for (i in 1..15) {
            dataList.add("사용자 $i")
        }
    }

    private fun setRecyclerView() {
        rv_act_notice.layoutManager = LinearLayoutManager(this)
        rv_act_notice.adapter = NoticeAdapter(this, dataList)
    }


}
