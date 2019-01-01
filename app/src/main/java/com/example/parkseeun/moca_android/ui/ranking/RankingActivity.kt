package com.example.parkseeun.moca_android.ui.ranking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_ranking.*

class RankingActivity : AppCompatActivity() {
    lateinit var rankingViewAdapter : RankingViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        setRecyclerView()
    }
    private fun setRecyclerView() {
        // 임시 데이터
        var dataList : ArrayList<RankingData> = ArrayList()
        dataList.add(RankingData(1,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        dataList.add(RankingData(2,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        dataList.add(RankingData(3,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        dataList.add(RankingData(4,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        dataList.add(RankingData(5,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        dataList.add(RankingData(6,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        dataList.add(RankingData(7,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        dataList.add(RankingData(8,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        dataList.add(RankingData(9,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        dataList.add(RankingData(10,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        dataList.add(RankingData(11,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        dataList.add(RankingData(12,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        dataList.add(RankingData(13,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        dataList.add(RankingData(14,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        dataList.add(RankingData(15,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))

        rankingViewAdapter = RankingViewAdapter(applicationContext!!, dataList)
        ranking_cafe_rv.adapter = rankingViewAdapter
        ranking_cafe_rv.layoutManager = GridLayoutManager(applicationContext,2)
    }
}
