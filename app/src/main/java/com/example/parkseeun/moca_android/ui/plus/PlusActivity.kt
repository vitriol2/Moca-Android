package com.example.parkseeun.moca_android.ui.plus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.*
import com.example.parkseeun.moca_android.network.ApplicationController
import kotlinx.android.synthetic.main.activity_plus.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlusActivity : AppCompatActivity() {

    // RecyclerView 설정
    lateinit var plusRecyclerViewAdapter : PlusRecyclerViewAdapter

    // 통신
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var getMocaPlusResponse : Call<GetMocaplusResponse> // 모카 플러스 리스트 조회
    var dataList : ArrayList<MocaplusData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plus)
        ib_plus_back.setOnClickListener {
            finish()
        }

        getMocaPlusList()
    }

    // 통신 (모카 플러스 리스트 조회)
    private fun getMocaPlusList() {
        getMocaPlusResponse = networkService.getMocaplusResponse(-1)
        getMocaPlusResponse.enqueue(object : Callback<GetMocaplusResponse> {
            override fun onFailure(call: Call<GetMocaplusResponse>, t: Throwable) {
                toast(t.message.toString())
            }

            override fun onResponse(call: Call<GetMocaplusResponse>, response: Response<GetMocaplusResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        dataList = response.body()!!.data
                        setRecyclerView()
                    }
                    else {
                        toast(response.body()!!.status.toString() + " : " + response.body()!!.messge)
                    }
                }
            }
        })
    }

    // RecyclerView 설정
    private fun setRecyclerView() {
        plusRecyclerViewAdapter = PlusRecyclerViewAdapter(this, dataList)
        rv_plus_list.adapter = plusRecyclerViewAdapter
        rv_plus_list.layoutManager = LinearLayoutManager(this)
    }
}
