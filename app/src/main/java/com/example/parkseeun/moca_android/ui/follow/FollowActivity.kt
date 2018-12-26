package com.example.parkseeun.moca_android.ui.follow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_follow.*

class FollowActivity : AppCompatActivity() {

    // RecyclerView 설정
    lateinit var followRecyclerViewAdapter : FollowRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow)

        setRecyclerView()
    }

    // RecyclerView 설정
    private fun setRecyclerView() {
        // 임시 데이터
        var dataList : ArrayList<FollowData> = ArrayList()
        dataList.add(FollowData("", "정화니", 0))
        dataList.add(FollowData("", "정화니", 0))
        dataList.add(FollowData("", "정화니", 0))

        followRecyclerViewAdapter = FollowRecyclerViewAdapter(applicationContext!!, dataList)
        rv_follow_list.adapter = followRecyclerViewAdapter
        rv_follow_list.layoutManager = LinearLayoutManager(applicationContext)
    }
}
