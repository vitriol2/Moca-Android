package com.example.parkseeun.moca_android.ui.main

import android.content.Intent
import com.example.parkseeun.moca_android.ui.ranking.RankingActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.example.parkseeun.moca_android.util.NavigationActivity
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetHomeHotplaceResponse
import com.example.parkseeun.moca_android.model.get.HomeHotplaceData
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.category.CategoryActivity
import com.example.parkseeun.moca_android.ui.mocapicks.MocaPicksListActivity
import com.example.parkseeun.moca_android.ui.plus.PlusActivity
import com.example.parkseeun.moca_android.util.SharedPreferenceController
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_home2.*
import kotlinx.android.synthetic.main.app_bar_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity2 : NavigationActivity(), View.OnClickListener{
    private val pickposts: ArrayList<String> = ArrayList()
    private val hotList = ArrayList<HomeHotplaceData>()
    val rankingPosts: ArrayList<CategoryRankData> = ArrayList()
    val plusPosts: ArrayList<String> = ArrayList()

    val networkService by lazy {
        ApplicationController.instance.networkService
    }
    lateinit var homeHotplaceAdapter: HomeHotplaceAdapter



    override fun onClick(v: View?) {
        when(v){
            home_picks_tv -> {
                startActivity(Intent(this, MocaPicksListActivity::class.java))
            }
            tv_act_home_hot_more -> {
               startActivity(Intent(this, CategoryActivity::class.java))
            }
            home_ranking_tv -> {
                startActivity(Intent(this, RankingActivity::class.java))
            }
            home_plus_tv -> {
                startActivity(Intent(this, PlusActivity::class.java))
            }
            home_menu_iv -> {
                drawer_layout.openDrawer(nav_view)
            }
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (p0.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }

        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)

        home_picks_tv.setOnClickListener(this)
        tv_act_home_hot_more.setOnClickListener(this)
        home_ranking_tv.setOnClickListener(this)
        home_plus_tv.setOnClickListener(this)
        home_menu_iv.setOnClickListener(this)

        makeData()

        getHomeHotplaceResponse()

        recyclerView()

        setHeader(nav_view)

        val snapHelper= LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_act_home_picks)

        // 수민 추가 (홈에서 검색 화면으로)
        home_search_iv.setOnClickListener {
            val intent : Intent = Intent(this@HomeActivity2, SearchActivity::class.java)

            startActivity(intent)
        }


//        val toggle = ActionBarDrawerToggle(
//            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
//        )
//        drawer_layout.addDrawerListener(toggle)
//        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }




    private fun recyclerView() {
        //cafecloud pick
        rv_act_home_picks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_act_home_picks.adapter = CategoryPickAdapter(this, pickposts)

        //concept
        homeHotplaceAdapter = HomeHotplaceAdapter(this, hotList)
        rv_act_home_concept.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_act_home_concept.adapter = homeHotplaceAdapter

        //ranking
        rv_act_home_ranking.layoutManager = LinearLayoutManager(this)
        rv_act_home_ranking.adapter = CategoryRankingAdapter(this, rankingPosts)

        //plus
        rv_act_home_plus.layoutManager = LinearLayoutManager(this)
        rv_act_home_plus.adapter = CategoryPlusAdapter(this, plusPosts)

        //
    }

    private fun makeData() {
        for (i in 1..12) {
            pickposts.add("카페 $i")


        }
        for (i in 1..3) {
            rankingPosts.add(CategoryRankData("cafe $i", "location $i"))
            plusPosts.add("image $i")

        }
    }

    private fun getHomeHotplaceResponse() {
        val token = SharedPreferenceController.getAuthorization(this)
        val getHomeHotplaceResponse = networkService.getHomeHotplaceResponse("application/json", token)

        getHomeHotplaceResponse.enqueue(object : Callback<GetHomeHotplaceResponse> {
            override fun onFailure(call: Call<GetHomeHotplaceResponse>, t: Throwable) {
                Log.e("Hotplace load failed", t.toString())
            }

            override fun onResponse(call: Call<GetHomeHotplaceResponse>, response: Response<GetHomeHotplaceResponse>) {
                if(response.isSuccessful) {
                    Log.v("hotplace", "load")
                    val temp : ArrayList<HomeHotplaceData> = response.body()!!.data
                    if(temp.size>0) {
                        val position = homeHotplaceAdapter.itemCount
                        homeHotplaceAdapter.dataList.addAll(temp)
                        homeHotplaceAdapter.notifyItemInserted(position)
                    }

                }
            }
        })
    }

}