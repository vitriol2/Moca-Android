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
import com.example.parkseeun.moca_android.model.get.GetMypageMembershipResponse
import com.example.parkseeun.moca_android.model.get.GetMypageScrapResponse
import com.example.parkseeun.moca_android.model.get.MembershipData
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

    private var mem_num: Int = 0
    lateinit var headerView: View
    private val membershipList = ArrayList<MembershipData>()
    private val memList = ArrayList<Boolean>()
    private var scrapList = ArrayList<String>()

    val networkService by lazy {
        ApplicationController.instance.networkService
    }

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


        val scrapCafe: RelativeLayout = headerView.findViewById(R.id.rl_mypage_tab_scrap_more) as RelativeLayout
        scrapCafe.setOnClickListener {
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

        val membershipRv: RecyclerView = headerView.findViewById(R.id.rv_act_home_membership)
        membershipRv.layoutManager = GridLayoutManager(this, 4)
        membershipRv.adapter = MypageTabAdapter(this, memList)



        setNetwork()

    }

    private fun setScrapList() {
        //찜한 카페 목록
        Glide.with(this).load(scrapList[0]).into(iv_mypage_tab_scrap_1)
        Glide.with(this).load(scrapList[1]).into(iv_mypage_tab_scrap_2)
        Glide.with(this).load(scrapList[2]).into(iv_mypage_tab_scrap_3)
        Glide.with(this).load(scrapList[3]).into(iv_mypage_tab_scrap_4)
        Glide.with(this).load(scrapList[4]).into(iv_mypage_tab_scrap_5)


    }

    private fun setMembershipList() {
        for (i in 1..mem_num) {
            if (mem_num != 0) {
                memList.add(true)
            } else
                break
        }
        for (i in mem_num + 1..12) {
            memList.add(false)
        }
    }

    private fun setNetwork() {
        //마이페이지-찜한카페 목록
        getMypageScrapResponse()

        //마이페이지-멤버십 개수
        getMypageMembershipResponse()

    }

    private fun getMypageScrapResponse() {
        val getMypageScrapResponse = networkService.getMypageScrapResponse("application/json", User.token!!)

        getMypageScrapResponse.enqueue(object : Callback<GetMypageScrapResponse> {
            override fun onFailure(call: Call<GetMypageScrapResponse>, t: Throwable) {
                Log.e("Mypage-Scrap failed", t.toString())
            }

            override fun onResponse(call: Call<GetMypageScrapResponse>, response: Response<GetMypageScrapResponse>) {
                if (response.isSuccessful) {
                    Log.v("mypage-scrap load", "success")
                    for (i in 1..5) {
                        if (response.body()!!.data != null) {
                            if (response.body()!!.data.size > 0) {
                                var scrapImg: String? = response.body()!!.data[i].cafe_img_url[i]
                                if (scrapImg != null) {
                                    scrapList.add(response.body()!!.data[i].cafe_img_url[i])
                                } else
                                    scrapList.add("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG")

                            } else
                                scrapList.add("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG")

                        }else
                            scrapList.add("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG")
                    }
                    Log.v("setList", "done")
                    setScrapList()
                    headerView.tv_act_home_membership_num!!.text = "$mem_num/12"
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
                call: Call<GetMypageMembershipResponse>, response: Response<GetMypageMembershipResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.data != null) {
                        membershipList.addAll(response.body()!!.data)
                        mem_num = membershipList.size
                    } else
                        mem_num = 0
                }
                setMembershipList()
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


