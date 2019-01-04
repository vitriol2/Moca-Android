package com.example.parkseeun.moca_android.ui.plus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_plus.*

class PlusActivity : AppCompatActivity() {

    // RecyclerView 설정
    lateinit var plusRecyclerViewAdapter : PlusRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plus)
        ib_plus_back.setOnClickListener {
            finish()
        }
        setRecyclerView()
    }

    // RecyclerView 설정
    private fun setRecyclerView() {
        // 임시 데이터
        var dataList : ArrayList<PlusData> = ArrayList()
        dataList.add(PlusData("", "", "크리스마스 화이링~~", "김정환"))
        dataList.add(PlusData("", "", "크리스마스 화이링~~", "김정환"))
        dataList.add(PlusData("", "", "크리스마스 화이링~~", "김정환"))
        dataList.add(PlusData("", "", "크리스마스 화이링~~", "김정환"))

        plusRecyclerViewAdapter = PlusRecyclerViewAdapter(applicationContext!!, dataList)
        rv_plus_list.adapter = plusRecyclerViewAdapter
        rv_plus_list.layoutManager = LinearLayoutManager(applicationContext)
    }
}
