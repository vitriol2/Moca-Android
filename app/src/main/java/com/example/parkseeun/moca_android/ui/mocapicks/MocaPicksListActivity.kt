package com.example.parkseeun.moca_android.ui.mocapicks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_moca_picks_detail.*
import kotlinx.android.synthetic.main.activity_moca_picks_list.*

class MocaPicksListActivity : AppCompatActivity() {

    // RecyclerView 설정
    lateinit var mocaPicksListAdapter : MocaPicksListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moca_picks_list)

        ib_mocaPicks_back.setOnClickListener {
            finish()
        }

        setRecyclerView() // RecyclerView 설정
    }

    // RecyclerView 설정
    private fun setRecyclerView() {
        // 임시 데이터
        var dataList : ArrayList<MocaPicksListData> = ArrayList()

        dataList.add(MocaPicksListData(1, "빔밤 카페", "안녕하세요 빔밤이에용", 2, "서울 강남구", "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "망원역"))
        dataList.add(MocaPicksListData(1, "빔밤 카페", "안녕하세요 빔밤이에용", 2, "서울 강남구", "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "망원역"))
        dataList.add(MocaPicksListData(1, "빔밤 카페", "안녕하세요 빔밤이에용", 2, "서울 강남구", "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "망원역"))
        dataList.add(MocaPicksListData(1, "빔밤 카페", "안녕하세요 빔밤이에용", 2, "서울 강남구", "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "망원역"))

        mocaPicksListAdapter = MocaPicksListAdapter(this, dataList)
        rv_mocaPicks_list.adapter = mocaPicksListAdapter
        rv_mocaPicks_list.layoutManager = LinearLayoutManager(applicationContext)

    }
}
