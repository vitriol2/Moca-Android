package com.example.parkseeun.moca_android.util

import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.*
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.category.CategoryActivity
import com.example.parkseeun.moca_android.ui.community.feed.FeedActivity
import com.example.parkseeun.moca_android.ui.main.*
import com.example.parkseeun.moca_android.ui.location.LocationSearchActivity
import com.example.parkseeun.moca_android.ui.main.EditProfileActivity
import com.example.parkseeun.moca_android.ui.main.HomeActivity2
import com.example.parkseeun.moca_android.ui.main.MypageTabAdapter
import com.example.parkseeun.moca_android.ui.main.notice.NoticeActivity
import com.example.parkseeun.moca_android.ui.main.coupon.CouponActivity
import com.example.parkseeun.moca_android.ui.mypage.coupon.HistoryActivity
import com.example.parkseeun.moca_android.ui.plus.PlusActivity
import kotlinx.android.synthetic.main.activity_home2.*
import kotlinx.android.synthetic.main.mypage_tab.*
import kotlinx.android.synthetic.main.mypage_tab.view.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = "NavigationActivity"

    private var mem_num: Int = 4
    lateinit var headerView: View
    private val membershipList = ArrayList<MembershipData>()
    val mList = ArrayList<Boolean>()
    val memList by lazy {
        ArrayList<Boolean>()
    }
    lateinit var mypageTabAdapter: MypageTabAdapter
    private val scrapList = ArrayList<String>(5)
    val networkService by lazy {
        ApplicationController.instance.networkService
    }
    lateinit var membershipRv: RecyclerView

    fun setHeader(view_navi: NavigationView) {


        headerView = view_navi.getHeaderView(0)

        val notice: RelativeLayout = headerView.findViewById(R.id.iv_mypage_tab_notice) as RelativeLayout
        notice.setOnClickListener {
            startActivity<NoticeActivity>()
        }

        val setting: ImageView = headerView.findViewById(R.id.iv_mypage_tab_setting) as ImageView
        setting.setOnClickListener {

            startActivity<EditProfileActivity>()
        }

        val home: ImageView = headerView.findViewById(R.id.iv_mypage_tab_home) as ImageView
        home.setOnClickListener {

            val intent = Intent(this, HomeActivity2::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        val category: ImageView = headerView.findViewById(R.id.iv_mypage_tab_category) as ImageView
        category.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        val location: ImageView = headerView.findViewById(R.id.iv_mypage_tab_location) as ImageView
        location.setOnClickListener {
            val intent = Intent(this, LocationSearchActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        val community: ImageView = headerView.findViewById(R.id.iv_mypage_tab_community) as ImageView
        community.setOnClickListener {
            val intent = Intent(this, FeedActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        val scrapCafeTitle : RelativeLayout = headerView.findViewById(R.id.rl_mypage_tab_scrap_title) as RelativeLayout
        val scrapCafe: RelativeLayout = headerView.findViewById(R.id.rl_mypage_tab_scrap_more) as RelativeLayout
        scrapCafe.setOnClickListener {
            startActivity<ScrapCafeActivity>()
        }
        scrapCafeTitle.setOnClickListener {
            startActivity<ScrapCafeActivity>()
        }

        val coupon: LinearLayout = headerView.findViewById(R.id.ll_mypage_tab_coupon) as LinearLayout
        coupon.setOnClickListener {
            startActivity<CouponActivity>()
        }

        val membership: RelativeLayout = headerView.findViewById(R.id.rl_mypage_tab_membership)
        membership.setOnClickListener {
            val intent = Intent(this@NavigationActivity, HistoryActivity::class.java)

            startActivity(intent)

        }

        setList()

        membershipRv = headerView.findViewById(R.id.rv_act_home_membership)
        membershipRv.layoutManager = GridLayoutManager(this, 4)
        membershipRv.adapter = MypageTabAdapter(this, mList)

        val mNum: String = mem_num.toString()
        headerView.tv_act_home_membership_num.text = mNum + "/12"



        setNetwork()

    }

    private fun setList() {
        for (i in 0..11) {
            if (i < mem_num) {
                mList.add(true)
            } else
                mList.add(false)
        }
    }

    private fun setScrapList() {
        //찜한 카페 목록

        if (scrapList[0].isNotEmpty()) {
            val scrap1 = headerView.iv_mypage_tab_scrap_1
            scrap1.visibility = View.VISIBLE
            Glide.with(this).load(scrapList[0]).into(scrap1)
        }
        if (scrapList[1].isNotEmpty()) {
            val scrap2 = headerView.iv_mypage_tab_scrap_2
            scrap2.visibility = View.VISIBLE
            Glide.with(this).load(scrapList[1]).into(scrap2)
        }
        if (scrapList[2].isNotEmpty()) {
            val scrap3 = headerView.iv_mypage_tab_scrap_3
            scrap3.visibility = View.VISIBLE
            Glide.with(this).load(scrapList[2]).into(scrap3)
        }
        if (scrapList[3].isNotEmpty()) {
            val scrap4 = headerView.iv_mypage_tab_scrap_4
            scrap4.visibility = View.VISIBLE
            Glide.with(this).load(scrapList[3]).into(scrap4)
        }
        if (scrapList[4].isNotEmpty()) {
            val scrap5 = headerView.iv_mypage_tab_scrap_5
            val scrap5Layout = headerView.rl_mypage_tab_scrap_more
            scrap5Layout.visibility = View.VISIBLE
            Glide.with(this).load(scrapList[4]).into(scrap5)
        }

    }


    private fun setNetwork() {
        //마이페이지-찜한카페 목록
        getMypageScrapResponse()

        //마이페이지-멤버십 개수
        getMypageMembershipResponse()

    }

    private fun getMypageScrapResponse() {
        val getMypageScrapResponse = networkService.getMypageScrapResponse( User.token!!)

        getMypageScrapResponse.enqueue(object : Callback<GetMypageScrapResponse> {
            override fun onFailure(call: Call<GetMypageScrapResponse>, t: Throwable) {
                Log.e("Mypage-Scrap failed", t.toString())
            }

            override fun onResponse(call: Call<GetMypageScrapResponse>, response: Response<GetMypageScrapResponse>) {
                if (response.isSuccessful) {
                    Log.v("mypage-scrap load", "success")
                    val temp: ArrayList<ScrapCafeData>? = response.body()!!.data
                    if (temp!=null) {
                        if (temp!!.size > 0) {
                            if (temp!!.size >= 5) {
                                for (i in 1..temp.size) {
                                    if (temp[i - 1].cafe_img_url.size > 0) {
                                        scrapList.add(temp[i].cafe_img_url[0].cafe_img_url[0])
                                    } else
                                        scrapList.add("")
                                }
                            } else if (temp!!.size < 5) {
                                for (i in 1..5) {
                                    if (i <= temp.size) {
                                        if (temp[i - 1].cafe_img_url.size > 0) {
                                            scrapList.add(temp[i].cafe_img_url[0].cafe_img_url[0])
                                        } else
                                            scrapList.add("")
                                    } else
                                        scrapList.add("")
                                }
                            } else if (temp.isEmpty()) {
                                for (i in 1..5) {
                                    scrapList.add("")
                                }
                            }
                            Log.v(TAG, temp.size.toString())
                        }
                    }
                    else {
                        for(i in 1..5) {
                            scrapList.add("")
                        }
                    }

                    Log.v("setList", "done")
                    setScrapList()
                }
            }

        })
    }


    private fun getMypageMembershipResponse() {
        val getMypageMembershipResponse = networkService.getMypageMembershipResponse("application/json", User.token!!)

        getMypageMembershipResponse.enqueue(object : Callback<GetMypageMembershipResponse> {
            override fun onFailure(call: Call<GetMypageMembershipResponse>, t: Throwable) {
                Log.e(TAG, "getMypageMembershipResponse failed")
            }

            override fun onResponse(
                call: Call<GetMypageMembershipResponse>, response: Response<GetMypageMembershipResponse>
            ) {
                if (response.isSuccessful) {
                    Log.v(TAG, "getMypageMembershipResponse load success")
                    val temp: ArrayList<MembershipData>? = response.body()!!.data
                    if (temp!!.size > 0) {
                        for (i in 0..11) {
                            if (i < 3) {
                                memList.add(true)
                            } else
                                memList.add(false)
                        }
                    } else {
                        for (i in 0..11) {
                            memList.add(false)
                        }
                    }
                    val memSize = memList.size
                    Log.v("getMypageMember", "$memSize")

                }
            }
        })
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.home_activity2, menu)
        return true
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.


        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}


