package com.example.parkseeun.moca_android.ui.mypage

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
import com.example.parkseeun.moca_android.util.SharedPreferenceController
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_home2.*
import kotlinx.android.synthetic.main.mypage_tab.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = "NavigationActivity"

    private var mem_num: Int = 4
    lateinit var headerView: View
    val mList = ArrayList<Boolean>()
    val memList by lazy {
        ArrayList<Boolean>()
    }
    lateinit var mypageTabAdapter: MypageTabAdapter
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

        val scrapCafeTitle: RelativeLayout = headerView.findViewById(R.id.rl_mypage_tab_scrap_title) as RelativeLayout
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

        membershipRv = headerView.findViewById(R.id.rv_act_home_membership)
        mypageTabAdapter = MypageTabAdapter(this, mList)
        membershipRv.layoutManager = GridLayoutManager(this, 4)
        membershipRv.adapter = mypageTabAdapter

        val mNum: String = mem_num.toString()
        headerView.tv_act_home_membership_num.text = "$mNum/12"



        setNetwork()

    }


    private fun setScrapList(data: ArrayList<ScrapCafeData>) {
        //찜한 카페 목록
        var cnt = 0
        data.forEachIndexed { index, it ->
            when (index) {
                0 -> {
                    headerView.iv_mypage_tab_scrap_1.visibility = View.VISIBLE
                    Glide.with(this@NavigationActivity).load( if(it.cafe_img_url.size==0) "https://s3.ap-northeast-2.amazonaws.com/project-sopt/commonDefaultimage%403x.png" else it.cafe_img_url[0].cafe_img_url ).into(headerView.iv_mypage_tab_scrap_1)
                }
                1 -> {
                    headerView.iv_mypage_tab_scrap_2.visibility = View.VISIBLE
                    Glide.with(this@NavigationActivity).load( if(it.cafe_img_url.size==0) "https://s3.ap-northeast-2.amazonaws.com/project-sopt/commonDefaultimage%403x.png" else it.cafe_img_url[0].cafe_img_url ).into(headerView.iv_mypage_tab_scrap_2)
                }
                2 -> {
                    headerView.iv_mypage_tab_scrap_3.visibility = View.VISIBLE
                    Glide.with(this@NavigationActivity).load( if(it.cafe_img_url.size==0) "https://s3.ap-northeast-2.amazonaws.com/project-sopt/commonDefaultimage%403x.png" else it.cafe_img_url[0].cafe_img_url ).into(headerView.iv_mypage_tab_scrap_3)
                }
                3 -> {
                    headerView.iv_mypage_tab_scrap_4.visibility = View.VISIBLE
                    Glide.with(this@NavigationActivity).load( if(it.cafe_img_url.size==0) "https://s3.ap-northeast-2.amazonaws.com/project-sopt/commonDefaultimage%403x.png" else it.cafe_img_url[0].cafe_img_url ).into(headerView.iv_mypage_tab_scrap_4)
                }
                4 -> {
                    headerView.rl_mypage_tab_scrap_more.visibility = View.VISIBLE
                    Glide.with(this@NavigationActivity).load( if(it.cafe_img_url.size==0) "https://s3.ap-northeast-2.amazonaws.com/project-sopt/commonDefaultimage%403x.png" else it.cafe_img_url[0].cafe_img_url ).into(headerView.iv_mypage_tab_scrap_5)
                }
                else -> {
                    headerView.rl_mypage_tab_more.visibility = View.VISIBLE
                    cnt++
                }
            }
        }
        if(cnt!=0)
            headerView.tv_mypage_tab_more.text = "$cnt${headerView.tv_mypage_tab_more.text}"
    }


    private fun setNetwork() {
        // 마이페이지 - 내 정보
        getMypageProfileResponse()

        // 마이페이지 - 찜한 카페 목록
        getMypageScrapResponse()

        // 마이페이지 - 쿠폰 개수
        getMypageCouponResponse()

        // 마이페이지 -멤버십 개수
        getMypageMembershipResponse()
    }

    private fun getMypageScrapResponse() {
        val getMypageScrapResponse = networkService.getMypageScrapResponse(User.token)

        getMypageScrapResponse.enqueue(object : Callback<GetMypageScrapResponse> {
            override fun onFailure(call: Call<GetMypageScrapResponse>, t: Throwable) {
                Log.e("Mypage-Scrap failed", t.toString())
            }

            override fun onResponse(call: Call<GetMypageScrapResponse>, response: Response<GetMypageScrapResponse>) {
                if (response.isSuccessful)
                    if(response.body()!!.status==200){
                        setScrapList(response.body()!!.data)
                    }
            }
        })
    }

    private fun getMypageProfileResponse() {
        val getMypageCouponResponse = networkService.getMypageEditprofileResponse(User.token)
        getMypageCouponResponse.enqueue(object : Callback<GetMypageEditprofileResponse> {
            override fun onFailure(call: Call<GetMypageEditprofileResponse>?, t: Throwable?) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetMypageEditprofileResponse>?, response: Response<GetMypageEditprofileResponse>?) {
                if (response!!.isSuccessful)
                    if (response.body()!!.status == 200) {
                        if (response.body()!!.data.user_img_url != null)
                            Glide.with(this@NavigationActivity).load(response.body()!!.data.user_img_url).into(headerView.iv_mypage_tab_profile)
                        headerView.tv_mypage_tab_name.text = response.body()!!.data.user_name
                        headerView.tv_mypage_tab_status.text = response.body()!!.data.user_status_comment
                    }
            }

        })
    }

    private fun getMypageCouponResponse() {
        val getMypageCouponResponse = networkService.getMypageCouponResponse(User.token)
        getMypageCouponResponse.enqueue(object : Callback<GetMypageCouponResponse> {
            override fun onFailure(call: Call<GetMypageCouponResponse>?, t: Throwable?) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetMypageCouponResponse>?, res: Response<GetMypageCouponResponse>?) {
                if (res!!.isSuccessful)
                    if (res.body()!!.status == 200) {
                        if (res.body()!!.data != null)
                            headerView.tv_mypage_tab_coupon_num.text = res.body()!!.data.size.toString()
                    }
            }

        })
    }


    private fun getMypageMembershipResponse() {
        val token: String = SharedPreferenceController.getAuthorization(this)
        val getMypageMembershipResponse = networkService.getMypageMembershipResponse("application/json", token)

        getMypageMembershipResponse.enqueue(object : Callback<GetMypageMembershipResponse> {
            override fun onFailure(call: Call<GetMypageMembershipResponse>, t: Throwable) {
                Log.e(TAG, "getMypageMembershipResponse failed")
            }

            override fun onResponse(call: Call<GetMypageMembershipResponse>, response: Response<GetMypageMembershipResponse>) {
                if (response.code() == 500) {
                    mypageTabAdapter = MypageTabAdapter(this@NavigationActivity, arrayListOf(false, false, false, false, false, false, false, false, false, false, false, false))
                    membershipRv.layoutManager = GridLayoutManager(this@NavigationActivity, 4)
                    membershipRv.adapter = mypageTabAdapter
                    headerView.tv_act_home_membership_num.text = "0/12"
                }
                if (response.isSuccessful) {
                    Log.v(TAG, "getMypageMembershipResponse load success")
                    val temp: ArrayList<MembershipData>? = response.body()!!.data
                    if (temp!!.size > 0) {
                        for (i in 0..11) {
                            if (i < temp.size) {
                                memList.add(true)
                            } else
                                memList.add(false)
                        }
                    } else {
                        for (i in 0..11) {
                            memList.add(false)
                        }
                    }
                    Log.v("getMypageMember", "${temp.size}")

                    val position = mypageTabAdapter.itemCount
                    mypageTabAdapter.dataList.addAll(memList)
                    mypageTabAdapter.notifyItemInserted(position)
                    headerView.tv_act_home_membership_num.text = "${temp.size}/12"
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