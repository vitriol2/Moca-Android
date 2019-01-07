package com.example.parkseeun.moca_android.ui.plus.plusDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_plus_detail.*

class PlusDetailActivity : AppCompatActivity() {

    // RecyclerView 설정
    lateinit var plusDetailRecyclerViewAdapter: PlusDetailRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plus_detail)

        setRecyclerView()

        ib_plus_detail_back.setOnClickListener {
            finish()
        }
    }

    // RecyclerView 설정
    private fun setRecyclerView() {
        // 임시 데이터
        var dataList : ArrayList<PlusDetailData> = ArrayList()

        dataList.add(PlusDetailData("서울 강남구", "빔밤카페", "북한강을 바라보다 어느새 시간이 훌쩍, 복잡하고 어지럽게 움직이는 서울에서 도망치듯 빠져나와 따듯한 커피 한 잔 그리고 담백하고 촉촉한 베이커리까지 즐기며 한 껏 여유를 찾을 수 있던 이 곳. 웅장해 보이던 외관 안에는 오히려, 아늑하고 섬세한 손길이 가득했다."))
        dataList.add(PlusDetailData("서울 강남구", "빔밤카페", "북한강을 바라보다 어느새 시간이 훌쩍, 복잡하고 어지럽게 움직이는 서울에서 도망치듯 빠져나와 따듯한 커피 한 잔 그리고 담백하고 촉촉한 베이커리까지 즐기며 한 껏 여유를 찾을 수 있던 이 곳. 웅장해 보이던 외관 안에는 오히려, 아늑하고 섬세한 손길이 가득했다."))
        dataList.add(PlusDetailData("서울 강남구", "빔밤카페", "북한강을 바라보다 어느새 시간이 훌쩍, 복잡하고 어지럽게 움직이는 서울에서 도망치듯 빠져나와 따듯한 커피 한 잔 그리고 담백하고 촉촉한 베이커리까지 즐기며 한 껏 여유를 찾을 수 있던 이 곳. 웅장해 보이던 외관 안에는 오히려, 아늑하고 섬세한 손길이 가득했다."))

        plusDetailRecyclerViewAdapter = PlusDetailRecyclerViewAdapter(applicationContext!!, dataList)
        rv_plus_detail.adapter = plusDetailRecyclerViewAdapter
        rv_plus_detail.layoutManager = LinearLayoutManager(applicationContext)
    }
}
