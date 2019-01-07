package com.example.parkseeun.moca_android.ui.detail

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetFeedResponseData
import com.example.parkseeun.moca_android.model.get.GetFeedResponseImage
import com.example.parkseeun.moca_android.ui.community.feed.ReviewRecyclerViewAdapter
import com.example.parkseeun.moca_android.ui.community.review_write.WriteReviewActivity
import com.example.parkseeun.moca_android.ui.detail.detailReviewAll.ReviewAllActivity
import com.example.parkseeun.moca_android.ui.detail.nearbyList.NearbyListActivity
import com.example.parkseeun.moca_android.util.ImageAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.startActivity

class DetailActivity : AppCompatActivity() {

    var urlList = arrayOf("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG","http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG")

    private val sigList = ArrayList<String>()
    private val menuList = ArrayList<DetailMenuData>()
    private val reviewList : ArrayList<GetFeedResponseData> = ArrayList()
    private val nearbyList : ArrayList<DetailNearbyData> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        makeList()

        setRecyclerView()

        pageChangeListener()

        setOnBtnClickListeners()

    }

    private fun setOnBtnClickListeners() {
        btn_act_detail_review_all.setOnClickListener {
            startActivity<ReviewAllActivity>()
        }

        ll_act_detail_nearbyList.setOnClickListener {
            startActivity<NearbyListActivity>()
        }

        ib_detail_back.setOnClickListener {
            finish()
        }

        // 리뷰 쓰기
        tv_detail_write_review.setOnClickListener {
            val intent = Intent(this@DetailActivity, WriteReviewActivity::class.java)

            startActivity(intent)
        }

        // 전체 리뷰 보기
        iv_detail_review_all.setOnClickListener {
            val intent = Intent(this@DetailActivity, ReviewAllActivity::class.java)

            startActivity(intent)
        }

        // 주변 카페 리스트
        ll_act_detail_nearbyList.setOnClickListener {
            val intent = Intent(this@DetailActivity, NearbyListActivity::class.java)

            startActivity(intent)
        }
    }

    private fun makeList() {
        for(i in 1..7) {
            sigList.add("메뉴 $i")
            menuList.add(DetailMenuData("메뉴 $i", "$i.0"))
        }

        reviewList.add(GetFeedResponseData(1, 1, "coco", arrayListOf(GetFeedResponseImage(1,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG")), 3, "넘 별로였음", "ㄹㅇ 가지 마세요", "2018-12-22","아영스윗홈", "우주 최고 짱", "1분 전", 300, 4000, false, true))
        reviewList.add(GetFeedResponseData(1, 1, "coco", arrayListOf(GetFeedResponseImage(1,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG")), 3, "넘 별로였음", "ㄹㅇ 가지 마세요", "2018-12-22","아영스윗홈", "우주 최고 짱", "1분 전", 300, 4000, false, true))
        reviewList.add(GetFeedResponseData(1, 1, "coco", arrayListOf(GetFeedResponseImage(1,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG")), 3, "넘 별로였음", "ㄹㅇ 가지 마세요", "2018-12-22","아영스윗홈", "우주 최고 짱", "1분 전", 300, 4000, false, true))
        reviewList.add(GetFeedResponseData(1, 1, "coco", arrayListOf(GetFeedResponseImage(1,"http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG")), 3, "넘 별로였음", "ㄹㅇ 가지 마세요", "2018-12-22","아영스윗홈", "우주 최고 짱", "1분 전", 300, 4000, false, true))

        nearbyList.add(DetailNearbyData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        nearbyList.add(DetailNearbyData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        nearbyList.add(DetailNearbyData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))
        nearbyList.add(DetailNearbyData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페 이름이긔", 4, "서울시 우리집"))

    }

    private fun pageChangeListener() {

        pb_act_detail.max = urlList.size

        vp_act_detail.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) { // 이미지 넘길 떄 하는 일은 여기서,,
                pb_act_detail.progress = p0 + 1
            }
        })

        vp_act_detail.adapter = ImageAdapter(this, urlList)
    }

    private fun setRecyclerView() {
        //시그니처 메뉴
        rv_act_detail_signiture.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_act_detail_signiture.adapter = DetailSignitureAdapter(this, sigList)

        //모든 메뉴
        rv_act_detail_menu.layoutManager = LinearLayoutManager(this)
        rv_act_detail_menu.adapter = DetailMenuAdapter(this, menuList)



        rv_act_detail_cafe_review.adapter = ReviewRecyclerViewAdapter(this, reviewList)
        rv_act_detail_cafe_review.layoutManager = LinearLayoutManager(this)

        //주변카페
        rv_act_detail_nearby.layoutManager = GridLayoutManager(this, 2)
        rv_act_detail_nearby.adapter = DetailNearbyAdapter(this, nearbyList)
    }
}