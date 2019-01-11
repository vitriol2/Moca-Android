package com.example.parkseeun.moca_android.ui.communitySearch

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.*
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.detail.detailReviewAll.GridSpacingItemDecoration
import com.example.parkseeun.moca_android.ui.detail.detailReviewAll.ReviewAllPopularAdapter
import com.example.parkseeun.moca_android.ui.detail.detailReviewAll.ReviewAllRecentAdapter
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_community_search.*
import kotlinx.android.synthetic.main.fragment_com_sear_all.*
import kotlinx.android.synthetic.main.fragment_com_sear_cafe.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunitySearchActivity : AppCompatActivity() {
    private val TAG = "CommunitySearchActivity"

    private val allData = ArrayList<CommunitySearchAllData>()
    private val cafeData = ArrayList<CommunitySearchAllData>()
    private val userData = ArrayList<CommunitySearchAllData>()

    // 통신
    private val networkService = ApplicationController.instance.networkService

    private var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    // 이번주 리뷰 많은 카페
    private lateinit var getBestCafeListResponse: Call<GetBestCafeListResponse>
    private var getBestCafeListData = ArrayList<GetBestCafeListData>()
    lateinit var comSearAllReviewTopAdapter: ComSearAllReviewTopAdapter

    // 이번주 인기 많은 사용자
    private lateinit var getBestUserResponse: Call<GetBestUserResponse>
    private var getBestUserData = ArrayList<GetBestUserData>()
    lateinit var comSearAllPopUserAdapter: ComSearAllPopUserAdapter

    //검색후
    // 전체 데이터
    private lateinit var getCommunitySearchListResponse: Call<GetCommunitySearchListResponse>
    //인기 리뷰
    private var popularReviewList = ArrayList<ReviewData>()
    lateinit var reviewAllPopularAdapter : ReviewAllPopularAdapter
    //최신 리뷰
    private var reviewListOrderByLatest = ArrayList<ReviewData>()
    lateinit var reviewAllRecentAdapter : ReviewAllRecentAdapter
    //사용자
    private var searchUserList = ArrayList<SearchUserData>()
    lateinit var comSearUserAdapter : ComSearUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_search)

        setRecyclerView()

        setNetwork()

        setScreenConvert()

        setOnBtnClickListeners()

    }

    private fun setOnBtnClickListeners() {
        ib_act_com_sear_back.setOnClickListener {
            finish()
        }

        btn_act_com_sear_all.setOnClickListener {
            btn_act_com_sear_cafe.setTextColor(Color.parseColor("#e1b2a3"))
            btn_act_com_sear_cafe.setTextColor(Color.parseColor("#707070"))
            btn_act_com_sear_user.setTextColor(Color.parseColor("#707070"))

            view_all.setBackgroundColor(Color.parseColor("#e1b2a3"))
            view_cafe.setBackgroundColor(Color.parseColor("#ffffff"))
            view_location.setBackgroundColor(Color.parseColor("#ffffff"))

            ll_act_com_sear_all_all.visibility = View.VISIBLE
            nv_act_com_sear_cafetab.visibility = View.GONE
            nv_act_com_sear_usertab.visibility = View.GONE
        }

        btn_act_com_sear_cafe.setOnClickListener {
            btn_act_com_sear_all.setTextColor(Color.parseColor("#707070"))
            btn_act_com_sear_cafe.setTextColor(Color.parseColor("#e1b2a3"))
            btn_act_com_sear_user.setTextColor(Color.parseColor("#707070"))

            view_all.setBackgroundColor(Color.parseColor("#ffffff"))
            view_cafe.setBackgroundColor(Color.parseColor("#e1b2a3"))
            view_location.setBackgroundColor(Color.parseColor("#ffffff"))

            ll_act_com_sear_all_all.visibility = View.GONE
            nv_act_com_sear_cafetab.visibility = View.VISIBLE
            nv_act_com_sear_usertab.visibility = View.GONE
        }

        btn_act_com_sear_user.setOnClickListener {
            btn_act_com_sear_cafe.setTextColor(Color.parseColor("#707070"))
            btn_act_com_sear_cafe.setTextColor(Color.parseColor("#707070"))
            btn_act_com_sear_user.setTextColor(Color.parseColor("#e1b2a3"))

            view_all.setBackgroundColor(Color.parseColor("#ffffff"))
            view_cafe.setBackgroundColor(Color.parseColor("#ffffff"))
            view_location.setBackgroundColor(Color.parseColor("#e1b2a3"))

            ll_act_com_sear_all_all.visibility = View.GONE
            nv_act_com_sear_cafetab.visibility = View.GONE
            nv_act_com_sear_usertab.visibility = View.VISIBLE
        }
    }

    private fun setScreenConvert() {
        et_act_com_sear_search.addTextChangedListener(object : TextWatcher {

            //val layoutNothing : View = findViewById(R.id.ll_frag_com_sear_all_nothing)
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (s.toString() == "") {
                    ll_act_com_sear_all_nothing.visibility = View.VISIBLE
                    ll_act_com_sear_all_all.visibility = View.GONE
                    nv_act_com_sear_cafetab.visibility = View.GONE
                    nv_act_com_sear_usertab.visibility = View.GONE
                } else {
                    ll_act_com_sear_all_nothing.visibility = View.GONE
                    ll_act_com_sear_all_all.visibility = View.VISIBLE
                    getAllResult(et_act_com_sear_search.text.toString())
                }
            }
        })
    }

    private fun setRecyclerView() {
        // 검색 전 화면 - 이번주 리뷰 많은 카페 RecyclerView 설정
        comSearAllReviewTopAdapter = ComSearAllReviewTopAdapter(this, getBestCafeListData)
        rv_act_com_sear_all_reviewtop.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_act_com_sear_all_reviewtop.adapter = comSearAllReviewTopAdapter

        // 검색 전 - 이번주 인기 많은 사용자
        comSearAllPopUserAdapter = ComSearAllPopUserAdapter(this, getBestUserData)
        rv_act_comm_sear_all_popularuser.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_act_comm_sear_all_popularuser.adapter = comSearAllPopUserAdapter

        ///////////////////
        // 검색 후
        // 인기리뷰
        reviewAllPopularAdapter = ReviewAllPopularAdapter(this, popularReviewList)
        rv_act_com_sear_all_review.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_act_com_sear_all_review.adapter = reviewAllPopularAdapter

        rv_act_com_sear_cafe_popular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_act_com_sear_cafe_popular.adapter = reviewAllPopularAdapter

        //최신리뷰
        val spanCount = 3 // 3 columns
        val spacing = 40     // 50px
        val includeEdge = false
        rv_act_com_sear_cafe_recent.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
        reviewAllRecentAdapter = ReviewAllRecentAdapter(this, reviewListOrderByLatest)
        rv_act_com_sear_cafe_recent.layoutManager = GridLayoutManager(this, 3)
        rv_act_com_sear_cafe_recent.adapter = reviewAllRecentAdapter

        //사용자
        comSearUserAdapter = ComSearUserAdapter(this, searchUserList)
        rv_act_com_sear_all_user.layoutManager = LinearLayoutManager(this)
        rv_act_com_sear_all_user.adapter = comSearUserAdapter

    }

    private fun setNetwork() {
        getBestReviewCafe()

        getBestUser()
    }

    //통신 (이번주 리뷰 많은 카페)
    private fun getBestReviewCafe() {
        getBestCafeListResponse = networkService.getBestCafeList(User.token!!, 1)
        getBestCafeListResponse.enqueue(object : Callback<GetBestCafeListResponse> {
            override fun onFailure(call: Call<GetBestCafeListResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }

            override fun onResponse(call: Call<GetBestCafeListResponse>, response: Response<GetBestCafeListResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        val temp = response.body()!!.data

                        // 검색 전 화면 - 이번주 리뷰 많은 카페 RecyclerView 설정
                        val position = comSearAllReviewTopAdapter.itemCount
                        comSearAllReviewTopAdapter.dataList.addAll(temp)
                        comSearAllReviewTopAdapter.notifyItemInserted(position)
                    }
                }
            }
        })
    }


    // 통신 (이번주 인기 많은 사용자)
    private fun getBestUser() {
        getBestUserResponse = networkService.getBestUserList(User.token!!)
        getBestUserResponse.enqueue(object : Callback<GetBestUserResponse> {
            override fun onFailure(call: Call<GetBestUserResponse>, t: Throwable) {
                toast(t.message.toString())
            }

            override fun onResponse(call: Call<GetBestUserResponse>, response: Response<GetBestUserResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        val temp = response.body()!!.data
                        if (temp.size > 0) {
                            // 이번주 인기 많은 사용자
                            val position = comSearAllPopUserAdapter.itemCount
                            comSearAllPopUserAdapter.dataList.addAll(temp)
                            comSearAllPopUserAdapter.notifyItemInserted(position)
                        }
                    }
                }
            }
        })
    }


    // 통신 (전체 탭)
    private fun getAllResult(searchString: String) {
        getCommunitySearchListResponse = networkService.getCommunitySearchResult(User.token!!, searchString)
        getCommunitySearchListResponse.enqueue(object : Callback<GetCommunitySearchListResponse> {
            override fun onFailure(call: Call<GetCommunitySearchListResponse>, t: Throwable) {
                toast(t.message.toString())
            }

            override fun onResponse(
                call: Call<GetCommunitySearchListResponse>,
                response: Response<GetCommunitySearchListResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        var temp = response.body()!!.data

                        popularReviewList = temp.popularReviewList
                        reviewListOrderByLatest = temp.reviewListOrderByLatest
                        searchUserList = temp.searchUserList

                        val positionPopCafe = reviewAllPopularAdapter.itemCount
                        val positionRecCafe = reviewAllRecentAdapter.itemCount
                        val positionUser = comSearUserAdapter.itemCount

                        reviewAllPopularAdapter.dataList=popularReviewList
                        reviewAllPopularAdapter.notifyItemChanged(positionPopCafe)

                        reviewAllRecentAdapter.dataList=reviewListOrderByLatest
                        reviewAllRecentAdapter.notifyItemChanged(positionRecCafe)

                        comSearUserAdapter.dataList=searchUserList
                        comSearUserAdapter.notifyItemChanged(positionUser)


                    }
                }

            }
        })
    }


}


