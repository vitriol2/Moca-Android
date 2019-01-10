package com.example.parkseeun.moca_android.ui.detail

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.*
import com.example.parkseeun.moca_android.model.post.PostNearByCafeData
import com.example.parkseeun.moca_android.model.post.PostNearByCafeResponse
import com.example.parkseeun.moca_android.model.post.PostNearByCafeResponseData
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.community.feed.ReviewRecyclerViewAdapter
import com.example.parkseeun.moca_android.ui.community.review_write.WriteReviewActivity
import com.example.parkseeun.moca_android.ui.detail.detailReviewAll.ReviewAllActivity
import com.example.parkseeun.moca_android.ui.detail.nearbyList.NearbyListActivity
import com.example.parkseeun.moca_android.ui.detail.nearbyList.NearbyListData
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
    lateinit var menuImage: ImageView
    lateinit var rating: RatingBar
    private val option = ArrayList<ImageView>()
    lateinit var day: TextView
    lateinit var time: TextView
    lateinit var parking: ImageView
    lateinit var wifi: ImageView
    lateinit var smoking: ImageView
    lateinit var hour: ImageView
    lateinit var reservation: ImageView

    private var latitude : Double = 0.toDouble()
    private var longitude : Double = 0.toDouble()
    private var id : Int = 220



    var urlList = ArrayList<String?>()

    private val sigList = ArrayList<SignitureData>()
    private val menuList = ArrayList<DetailMenuData>()
    private val reviewList: ArrayList<GetFeedResponseData> = ArrayList()
    private val nearbyList: ArrayList<PostNearByCafeResponseData> = ArrayList()

    lateinit var detailSignitureAdapter: DetailSignitureAdapter
    lateinit var reviewRecyclerViewAdapter: ReviewRecyclerViewAdapter
    lateinit var detailNearbyAdapter : DetailNearbyAdapter


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setNetwork()

        matchView()

        makeList()

        setRecyclerView()


        setOnBtnClickListeners()

    }

    private fun setNetwork() {
        getCafeDetailResponse(this)

        getCafeDetailImageResponse()

        getCafeSignitureResponse()

        getCafePopReviewResponse()

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
        menuImage = findViewById(R.id.iv_act_detail_menu)

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

        rl_act_detail_menu.setOnClickListener {
            iv_act_detail_menu.visibility = View.VISIBLE
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


    }

    private fun makeList() {
        for (i in 1..7) {
            menuList.add(DetailMenuData("메뉴 $i", "$i.0"))
        }
/*
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
        */





    }

    private fun pageChangeListener(num: Int) {

        pb_act_detail.max = num

        vp_act_detail.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) { // 이미지 넘길 떄 하는 일은 여기서,,
                pb_act_detail.progress = p0 + 1
            }
        })


        vp_act_detail.adapter = ImageAdapter(this, urlList.toTypedArray())
        Log.v("image num", num.toString())
    }

    private fun setRecyclerView() {
        //시그니처 메뉴
        detailSignitureAdapter = DetailSignitureAdapter(this, sigList)
        rv_act_detail_signiture.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_act_detail_signiture.adapter = detailSignitureAdapter

        reviewRecyclerViewAdapter = ReviewRecyclerViewAdapter(this, reviewList)
        rv_act_detail_cafe_review.adapter = reviewRecyclerViewAdapter
        rv_act_detail_cafe_review.layoutManager = LinearLayoutManager(this)

        //주변카페
        detailNearbyAdapter = DetailNearbyAdapter(this, nearbyList)
        rv_act_detail_nearby.adapter = detailNearbyAdapter
        rv_act_detail_nearby.layoutManager = GridLayoutManager(this, 2)
    }

    private fun getCafeDetailResponse(context : Context) {
        val getCafeDetailResponse = networkService.getCafeDetailResponse(User.token, 1)

        getCafeDetailResponse.enqueue(object : Callback<GetCafeDetailResponse> {
            override fun onFailure(call: Call<GetCafeDetailResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }

            override fun onResponse(call: Call<GetCafeDetailResponse>, response: Response<GetCafeDetailResponse>) {
                if (response.isSuccessful) {
                    Log.v(TAG, "load success123 || getCafeDetailResponse")
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

                    Glide.with(this@DetailActivity).load(temp.cafe_menu_img_url).into(menuImage)

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

                    latitude = temp.cafe_latitude
                    longitude = temp.cafe_longitude
                    id = temp.cafe_id

                    postCafeNearbyResponse()


                }
            }
        })
    }

    private fun getCafeDetailImageResponse() {
        val getCafeDetailResponse = networkService.getCafeDetailImageResponse(User.token, 1)

        getCafeDetailResponse.enqueue(object : Callback<GetCafeDetailImageResponse> {
            override fun onFailure(call: Call<GetCafeDetailImageResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(
                call: Call<GetCafeDetailImageResponse>,
                response: Response<GetCafeDetailImageResponse>
            ) {
                if (response.isSuccessful) {
                    val temp: ArrayList<CafeDetailImageData> = response.body()!!.data
                    if (temp.size > 0) {
                        for (i in 0..temp.size - 1)
                            urlList.add(temp[i].cafe_img_url)

                        pageChangeListener(urlList.size)
                    }
                }
            }
        })
    }

    private fun getCafeSignitureResponse() {
        val getCafeSignitureResponse = networkService.getCafeSignitureResponse(User.token, 3)

        getCafeSignitureResponse.enqueue(object : Callback<GetCafeSignitureResponse> {
            override fun onFailure(call: Call<GetCafeSignitureResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(
                call: Call<GetCafeSignitureResponse>,
                response: Response<GetCafeSignitureResponse>
            ) {
                if (response.isSuccessful) {
                    val temp: ArrayList<SignitureData> = response.body()!!.data
                    if (temp.size > 0) {

                        val position = detailSignitureAdapter.itemCount
                        detailSignitureAdapter.dataList.addAll(temp)
                        detailSignitureAdapter.notifyItemInserted(position)

                    }
                }
            }
        })
    }

    private fun getCafePopReviewResponse(){
        val getCafePopReviewResponse = networkService.getCafePopReviewResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiY29jbyIsImlzcyI6IkRvSVRTT1BUIn0.Rplge4ISuuCrFzrddjOl55TCeRQ2QUD9yuwSMmOZ5X0", 1)

        Log.v("token", "---"+User.token)

        getCafePopReviewResponse.enqueue(object : Callback<GetCafePopReviewResponse>{
            override fun onFailure(call: Call<GetCafePopReviewResponse>, t: Throwable) {
                Log.e("cafePopReview", t.toString())
            }

            override fun onResponse(call: Call<GetCafePopReviewResponse>, response: Response<GetCafePopReviewResponse>) {
                if(response.isSuccessful) {
                    val temp : ArrayList<GetFeedResponseData> = response.body()!!.data
                    if(temp.size>0){
                        val position = reviewRecyclerViewAdapter.itemCount
                        reviewRecyclerViewAdapter.dataList.addAll(temp)
                        reviewRecyclerViewAdapter.notifyItemInserted(position)

                        Log.v("cafePopReview", temp.size.toString())
                    }
                }
                Log.v("onResponse", response.raw().toString())
            }
        })
    }

    private fun postCafeNearbyResponse() {
        val nearbyData : PostNearByCafeData = PostNearByCafeData(latitude.toString(), longitude.toString(), 220, 1)
        Log.v("postNearby", latitude.toString())

        val postCafeNearbyResponse = networkService.postCafeNearbyResponse(User.token, nearbyData)

        postCafeNearbyResponse.enqueue(object : Callback<PostNearByCafeResponse>{
            override fun onFailure(call: Call<PostNearByCafeResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<PostNearByCafeResponse>, response: Response<PostNearByCafeResponse>) {
                if(response.isSuccessful) {
                    Log.v("nearbyResponse", "load success123")

                    if(response.body()!!.data!=null) {
                        val temp: ArrayList<PostNearByCafeResponseData> = response.body()!!.data

                        if (temp.size > 0) {
                            Log.v("nearbydata", temp.size.toString())
                            val position = detailNearbyAdapter.itemCount
                            detailNearbyAdapter.dataList.addAll(temp)
                            detailNearbyAdapter.notifyItemInserted(position)






                        }
                    }
                }
            }
        })
    }
}