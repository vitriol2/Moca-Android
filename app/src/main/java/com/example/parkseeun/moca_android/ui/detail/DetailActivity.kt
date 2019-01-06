package com.example.parkseeun.moca_android.ui.detail

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.View
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.community.feed.ReviewData
import com.example.parkseeun.moca_android.ui.community.feed.ReviewRecyclerViewAdapter
import com.example.parkseeun.moca_android.ui.community.review_write.WriteReviewActivity
import com.example.parkseeun.moca_android.ui.detail.detailReviewAll.ReviewAllActivity
import com.example.parkseeun.moca_android.ui.detail.nearbyList.NearbyListActivity
import com.example.parkseeun.moca_android.ui.ranking.RankingData
import com.example.parkseeun.moca_android.util.ImageAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_moca_picks_detail.*
import kotlinx.android.synthetic.main.fragment_social.view.*
import org.jetbrains.anko.startActivity

class DetailActivity : AppCompatActivity() {

    var urlList = arrayOf("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG","http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG")

    private val sigList = ArrayList<String>()
    private val menuList = ArrayList<DetailMenuData>()
    private val reviewList : ArrayList<ReviewData> = ArrayList()
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

        reviewList.add(ReviewData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4","정화니",4,0, "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4",199, 200, "C27", "20시간 전","서울 강남구", "오께~이 간만에 커필 마셨는데 음~ 가격~이… 잘 모르겠쒀요"))
        reviewList.add(ReviewData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4","누구",3,1, "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4",199, 200, "C27", "20시간 전","서울 강남구", "오께~이 간만에 커필 마셨는데 음~ 가격~이… 잘 모르겠쒀요"))
        reviewList.add(ReviewData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4","세용",2,1, "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4",199, 200, "C27", "20시간 전","서울 강남구", "오께~이 간만에 커필 마셨는데 음~ 가격~이… 잘 모르겠쒀요"))
        reviewList.add(ReviewData("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4","아용",1,0, "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4",199, 200, "C27", "20시간 전","서울 강남구", "노 맛"))

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
