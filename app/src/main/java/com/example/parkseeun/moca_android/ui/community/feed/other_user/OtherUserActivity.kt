package com.example.parkseeun.moca_android.ui.community.feed.other_user

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.community.feed.ReviewData
import com.example.parkseeun.moca_android.ui.community.feed.ReviewRecyclerViewAdapter
import com.example.parkseeun.moca_android.ui.community.follow.FollowActivity
import kotlinx.android.synthetic.main.activity_other_user.*

class OtherUserActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var reviewRecyclerViewAdapter : ReviewRecyclerViewAdapter

    override fun onClick(v: View?) {
        when(v){
            other_follower_tv -> {
                Intent(this, FollowActivity::class.java).apply {
                    this.putExtra("flag","follower")
                    startActivity(this)
                }
            }
            other_following_tv -> {
                Intent(this, FollowActivity::class.java).apply {
                    putExtra("flag","following")
                    startActivity(this)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_user)

        other_follower_tv.setOnClickListener(this)
        other_following_tv.setOnClickListener(this)

        setRecyclerView()
    }
    // RecyclerView 설정
    private fun setRecyclerView() {
        // 임시 데이터
        var dataList : ArrayList<ReviewData> = ArrayList()
        dataList.add(ReviewData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "정화니", 4, 0, "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", 199, 200, "C27", "20시간 전", "서울 강남구", "오께~이 간만에 커필 마셨는데 음~ 가격~이… 잘 모르겠쒀요"))
        dataList.add(ReviewData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "누구", 3, 1, "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", 199, 200, "C27", "20시간 전", "서울 강남구", "오께~이 간만에 커필 마셨는데 음~ 가격~이… 잘 모르겠쒀요"))
        dataList.add(ReviewData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "세용", 2, 1, "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", 199, 200, "C27", "20시간 전", "서울 강남구", "오께~이 간만에 커필 마셨는데 음~ 가격~이… 잘 모르겠쒀요"))
        dataList.add(ReviewData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "아용", 1, 0, "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", 199, 200, "C27", "20시간 전", "서울 강남구", "노 맛"))

        reviewRecyclerViewAdapter = ReviewRecyclerViewAdapter(applicationContext!!, dataList)
        other_reviews_recycler.adapter = reviewRecyclerViewAdapter
        other_reviews_recycler.layoutManager = LinearLayoutManager(applicationContext)
    }
}
