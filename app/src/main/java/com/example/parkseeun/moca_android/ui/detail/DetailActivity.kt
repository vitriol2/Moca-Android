package com.example.parkseeun.moca_android.ui.detail

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.CafeDetailData
import com.example.parkseeun.moca_android.model.get.GetCafeDetailResponse
import com.example.parkseeun.moca_android.model.get.GetFeedResponseData
import com.example.parkseeun.moca_android.model.get.GetFeedResponseImage
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.community.feed.ReviewRecyclerViewAdapter
import com.example.parkseeun.moca_android.ui.community.review_write.WriteReviewActivity
import com.example.parkseeun.moca_android.ui.detail.detailReviewAll.ReviewAllActivity
import com.example.parkseeun.moca_android.ui.detail.nearbyList.NearbyListActivity
import com.example.parkseeun.moca_android.util.ImageAdapter
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private val networkService by lazy {
        ApplicationController.instance.networkService
    }

    private val TAG = "DetailActivity"

    private var cafe_id: Int = 1

    lateinit var cafename: TextView
    lateinit var phone: TextView
    lateinit var address: TextView
    private var menuImage: String? = null
    lateinit var rating: RatingBar
    private val  option = ArrayList<ImageView>()
    lateinit var day: TextView
    lateinit var time: TextView
    lateinit var parking: ImageView
    lateinit var wifi: ImageView
    lateinit var smoking: ImageView
    lateinit var hour: ImageView
    lateinit var reservation: ImageView


    var urlList: Array<String?> = arrayOf(
        "c",
        "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG",
        "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG",
        "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG",
        "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG"
    )

    private val sigList = ArrayList<String>()
    private val menuList = ArrayList<DetailMenuData>()
    private val reviewList: ArrayList<GetFeedResponseData> = ArrayList()
    private val nearbyList: ArrayList<DetailNearbyData> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setNetwork()

        matchView()

        makeList()

        setRecyclerView()

        pageChangeListener()

        setOnBtnClickListeners()

    }

    private fun setNetwork() {
        getCafeDetailResponse()
    }

    private fun matchView() {
        cafename = findViewById(R.id.tv_act_detail_cafename)
        phone = findViewById(R.id.tv_act_detail_phone)
        address = findViewById(R.id.tv_act_detail_address)
        rating = findViewById(R.id.rb_act_detail_rating)
        day = findViewById(R.id.tv_act_detail_day)
        time = findViewById(R.id.tv_act_detail_time)
        parking = findViewById(R.id.iv_act_detail_parking)
        wifi = findViewById(R.id.iv_act_detail_wifi)
        smoking = findViewById(R.id.iv_act_detail_smoking)
        hour = findViewById(R.id.iv_act_detail_24)
        reservation = findViewById(R.id.iv_act_detail_reservation)

        option.add(parking)
        option.add(wifi)
        option.add(smoking)
        option.add(hour)
        option.add(reservation)

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
        for (i in 1..7) {
            sigList.add("메뉴 $i")
            menuList.add(DetailMenuData("메뉴 $i", "$i.0"))
        }

        reviewList.add(
            GetFeedResponseData(
                1,
                1,
                "coco",
                "아영",
                "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG",
                arrayListOf(
                    GetFeedResponseImage(
                        1,
                        "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG"
                    )
                ),
                3,
                "넘 별로였음",
                "ㄹㅇ 가지 마세요",
                "2018-12-22",
                "아영스윗홈",
                "우주 최고 짱",
                "1분 전",
                300,
                4000,
                false,
                true
            )
        )
        reviewList.add(
            GetFeedResponseData(
                1,
                1,
                "coco",
                "아영",
                "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG",
                arrayListOf(
                    GetFeedResponseImage(
                        1,
                        "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG"
                    )
                ),
                3,
                "넘 별로였음",
                "ㄹㅇ 가지 마세요",
                "2018-12-22",
                "아영스윗홈",
                "우주 최고 짱",
                "1분 전",
                300,
                4000,
                false,
                true
            )
        )
        reviewList.add(
            GetFeedResponseData(
                1,
                1,
                "coco",
                "아영",
                "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG",
                arrayListOf(
                    GetFeedResponseImage(
                        1,
                        "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG"
                    )
                ),
                3,
                "넘 별로였음",
                "ㄹㅇ 가지 마세요",
                "2018-12-22",
                "아영스윗홈",
                "우주 최고 짱",
                "1분 전",
                300,
                4000,
                false,
                true
            )
        )
        reviewList.add(
            GetFeedResponseData(
                1,
                1,
                "coco",
                "아영",
                "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG",
                arrayListOf(
                    GetFeedResponseImage(
                        1,
                        "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG"
                    )
                ),
                3,
                "넘 별로였음",
                "ㄹㅇ 가지 마세요",
                "2018-12-22",
                "아영스윗홈",
                "우주 최고 짱",
                "1분 전",
                300,
                4000,
                false,
                true
            )
        )

        nearbyList.add(
            DetailNearbyData(
                "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG",
                "카페 이름이긔",
                4,
                "서울시 우리집"
            )
        )
        nearbyList.add(
            DetailNearbyData(
                "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG",
                "카페 이름이긔",
                4,
                "서울시 우리집"
            )
        )
        nearbyList.add(
            DetailNearbyData(
                "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG",
                "카페 이름이긔",
                4,
                "서울시 우리집"
            )
        )
        nearbyList.add(
            DetailNearbyData(
                "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG",
                "카페 이름이긔",
                4,
                "서울시 우리집"
            )
        )

    }

    private fun pageChangeListener() {

        pb_act_detail.max = urlList.size

        vp_act_detail.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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

        rv_act_detail_cafe_review.adapter = ReviewRecyclerViewAdapter(this, reviewList)
        rv_act_detail_cafe_review.layoutManager = LinearLayoutManager(this)

        //주변카페
        rv_act_detail_nearby.layoutManager = GridLayoutManager(this, 2)
        rv_act_detail_nearby.adapter = DetailNearbyAdapter(this, nearbyList)
    }

    private fun getCafeDetailResponse() {
        val getCafeDetailResponse = networkService.getCafeDetailResponse(User.token, 1)

        getCafeDetailResponse.enqueue(object : Callback<GetCafeDetailResponse> {
            override fun onFailure(call: Call<GetCafeDetailResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }

            override fun onResponse(call: Call<GetCafeDetailResponse>, response: Response<GetCafeDetailResponse>) {
                if (response.isSuccessful) {
                    Log.v(TAG, "load success")
                    val temp: CafeDetailData = response.body()!!.data
                    cafename.text = temp.cafe_name
                    rating.rating = temp.cafe_rating_avg.toFloat()
                    Log.v("rating", rating.numStars.toString())
                    Log.v("rating temp", temp.cafe_rating_avg.toString())
                    address.text = temp.address_distrival + temp.cafe_address_detail
                    phone.text = temp.cafe_phone
                    day.text = temp.cafe_days
                    time.text = temp.cafe_times
                    val cOptions = ArrayList<Boolean>()
                    cOptions.add(temp.cafe_option_parking)
                    cOptions.add(temp.cafe_option_wifi)
                    cOptions.add(temp.cafe_option_smokingarea)
                    cOptions.add(temp.cafe_option_allnight)
                    cOptions.add(temp.cafe_option_reservation)

                    Glide.with(this@DetailActivity).load(
                        if (cOptions[0]) R.drawable.detila_info_parking_pink
                        else R.drawable.detail_info_parking_gray
                    ).into(option[0])
                    Glide.with(this@DetailActivity).load(
                        if (cOptions[0]) R.drawable.detail_info_wifi_pink
                        else R.drawable.detail_info_wifi_gray
                    ).into(option[1])
                    Glide.with(this@DetailActivity).load(
                        if (cOptions[0]) R.drawable.detail_info_smoking_pink
                        else R.drawable.detail_info_smoking_gray
                    ).into(option[2])
                    Glide.with(this@DetailActivity).load(
                        if (cOptions[0]) R.drawable.detail_info_24_h_pink
                        else R.drawable.detail_info_24_h_gray
                    ).into(option[3])
                    Glide.with(this@DetailActivity).load(
                        if (cOptions[0]) R.drawable.detail_info_reservation_pink
                        else R.drawable.detail_info_reservation_gray
                    ).into(option[4])


                }
            }
        })
    }
}