package com.example.parkseeun.moca_android.ui.mocapicks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.*
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.main.BeforeSearchPopularCafeAdapter
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_moca_picks_detail.*
import kotlinx.android.synthetic.main.activity_moca_picks_list.*
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MocaPicksListActivity : AppCompatActivity() {

    // RecyclerView 설정
    lateinit var mocaPicksListAdapter : MocaPicksListAdapter
    var dataList : ArrayList<GetMocaPicksListData> = ArrayList()

    // 통신
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var getMocaPicksListResponse: Call<GetMocaPicksListResponse> // 검색

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moca_picks_list)

        ib_mocaPicks_back.setOnClickListener {
            finish()
        }

        getMocaPicksList()
        setRecyclerView() // RecyclerView 설정
    }

    // 통신
    private fun getMocaPicksList() {
        getMocaPicksListResponse = networkService.getMocaPicksList(User.token!!, -1)
        getMocaPicksListResponse.enqueue(object: Callback<GetMocaPicksListResponse> {
            override fun onFailure(call: Call<GetMocaPicksListResponse>, t: Throwable) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetMocaPicksListResponse>, response: Response<GetMocaPicksListResponse>) {
                if(response.isSuccessful) {
                    if(response.body()!!.status==200) {
                        dataList = response.body()!!.data
                    }
                    else {
                        toast(response.body()!!.status.toString() + " : " + response.body()!!.message)
                    }
                }
            }
        })
    }

    // RecyclerView 설정
    private fun setRecyclerView() {
        // 임시 데이터
        mocaPicksListAdapter = MocaPicksListAdapter(this, dataList)
        rv_mocaPicks_list.adapter = mocaPicksListAdapter
        rv_mocaPicks_list.layoutManager = LinearLayoutManager(applicationContext)
    }
}
