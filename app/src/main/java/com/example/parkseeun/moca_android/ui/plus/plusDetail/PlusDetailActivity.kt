package com.example.parkseeun.moca_android.ui.plus.plusDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.*
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_plus_detail.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlusDetailActivity : AppCompatActivity() {

    // RecyclerView 설정
    lateinit var plusDetailRecyclerViewAdapter: PlusDetailRecyclerViewAdapter
    var plus_subject_id = -1

    // 통신
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var getMocaPlusDetailResponse: Call<GetMocaPlusDetailResponse> // 모카 플러스 리스트 조회
    private lateinit var mocaPlusData : MocaplusData

    // 카페 리스트
    private lateinit var getMocaPlusDetailCafeListResponse : Call<GetMocaPlusDetailCafeListResponse>
    private var getMocaPlusDetailCafeList = ArrayList<GetMocaPlusDetailCafeListData>()
    private lateinit var getMocaContentImagesData : ArrayList<GetMocaContentImagesData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plus_detail)

        ib_plus_detail_back.setOnClickListener {
            finish()
        }

        plus_subject_id = this.intent.getIntExtra("plus_subject_id", -1)

        getSubjectInfo()
        getCafeList()
    }

    // 통신 (상세보기 데이터)
    private fun getSubjectInfo() {
        getMocaPlusDetailResponse = networkService.getMocaPlusDetail(plus_subject_id)
        getMocaPlusDetailResponse.enqueue(object : Callback<GetMocaPlusDetailResponse> {
            override fun onFailure(call: Call<GetMocaPlusDetailResponse>, t: Throwable) {
                toast(t.message.toString())
            }

            override fun onResponse(call: Call<GetMocaPlusDetailResponse>, response: Response<GetMocaPlusDetailResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        mocaPlusData = response.body()!!.data

                        tv_plus_detail_editor_name.text = mocaPlusData.editor_name
                        tv_plus_detail_contentsTitle.text = mocaPlusData.plus_subject_title
                        Glide.with(this@PlusDetailActivity).load(mocaPlusData.plus_subject_img_url).into(rv_plus_detail_background)
                    }
                }
            }

        })
    }

    // 통신 (상세보기 카페 리스트)
    private fun getCafeList() {
        getMocaPlusDetailCafeListResponse = networkService.getMocaPlusDetailCafeList(User.token!!, plus_subject_id)
        getMocaPlusDetailCafeListResponse.enqueue(object : Callback<GetMocaPlusDetailCafeListResponse> {
            override fun onFailure(call: Call<GetMocaPlusDetailCafeListResponse>, t: Throwable) {
                toast(t.message.toString())
            }

            override fun onResponse(call: Call<GetMocaPlusDetailCafeListResponse>, response: Response<GetMocaPlusDetailCafeListResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        getMocaPlusDetailCafeList = response.body()!!.data

                        setRecyclerView()
                    }
                }
            }

        })
    }

    // RecyclerView 설정
    private fun setRecyclerView() {
        plusDetailRecyclerViewAdapter = PlusDetailRecyclerViewAdapter(this, getMocaPlusDetailCafeList)
        rv_plus_detail.adapter = plusDetailRecyclerViewAdapter
        rv_plus_detail.layoutManager = LinearLayoutManager(this)
    }
}
