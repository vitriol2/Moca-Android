package com.example.parkseeun.moca_android.ui.detail.nearbyList

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.detail.detailReviewAll.ReviewAllPopularData
import kotlinx.android.synthetic.main.activity_nearby_list.*

class NearbyListActivity : AppCompatActivity() {

    private val nearbyList = ArrayList<NearbyListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_list)

        makeList()

        setRecyclerView()

        setOnBtnClickListeners()
    }

    private fun setOnBtnClickListeners() {
        iv_act_nearby_list_back.setOnClickListener {
            finish()
        }
    }

    private fun setRecyclerView() {
        rv_act_nearby_list.layoutManager = LinearLayoutManager(this)
        rv_act_nearby_list.adapter = NearbyListAdapter(this, nearbyList)
    }

    private fun makeList() {
        for(i in 1..8) {
            nearbyList.add(NearbyListData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 $i", "장소 $i", 4))
        }
    }


}
