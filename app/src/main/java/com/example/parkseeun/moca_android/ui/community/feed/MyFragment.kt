package com.example.parkseeun.moca_android.ui.community.feed

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.community.follow.FollowActivity
import kotlinx.android.synthetic.main.fragment_my.*
import kotlinx.android.synthetic.main.fragment_my.view.*

class MyFragment :Fragment(), View.OnClickListener{
    lateinit var reviewRecyclerViewAdapter : ReviewRecyclerViewAdapter

    override fun onClick(v: View?) {
        when(v){
            my_follower_tv -> {
                Intent(context, FollowActivity::class.java).apply {
                    putExtra("flag","follower")
                    startActivity(this)
                }
            }
            my_following_tv -> {
                Intent(context, FollowActivity::class.java).apply {
                    putExtra("flag","following")
                    startActivity(this)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_my,container,false)

        v.my_follower_tv.setOnClickListener(this)
        v.my_following_tv.setOnClickListener(this)

        setRecyclerView(v)
        return v
    }
    // RecyclerView 설정
    private fun setRecyclerView(v:View) {
        // 임시 데이터
        var dataList : ArrayList<ReviewData> = ArrayList()
        dataList.add(ReviewData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4","정화니",4,0, "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4",199, 200, "C27", "20시간 전","서울 강남구", "오께~이 간만에 커필 마셨는데 음~ 가격~이… 잘 모르겠쒀요"))
        dataList.add(ReviewData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4","누구",3,1, "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4",199, 200, "C27", "20시간 전","서울 강남구", "오께~이 간만에 커필 마셨는데 음~ 가격~이… 잘 모르겠쒀요"))
        dataList.add(ReviewData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4","세용",2,1, "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4",199, 200, "C27", "20시간 전","서울 강남구", "오께~이 간만에 커필 마셨는데 음~ 가격~이… 잘 모르겠쒀요"))
        dataList.add(ReviewData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4","아용",1,0, "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4",199, 200, "C27", "20시간 전","서울 강남구", "노 맛"))

        reviewRecyclerViewAdapter = ReviewRecyclerViewAdapter(context!!, dataList)
        v.my_reviews_recycler.adapter = reviewRecyclerViewAdapter
        v.my_reviews_recycler.layoutManager = LinearLayoutManager(context)
    }
}