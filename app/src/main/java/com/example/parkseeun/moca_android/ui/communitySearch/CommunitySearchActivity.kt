package com.example.parkseeun.moca_android.ui.communitySearch

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.RelativeLayout
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.main.SearchAdapater
import com.example.parkseeun.moca_android.ui.main.SearchResultData
import kotlinx.android.synthetic.main.activity_community_search.*
import kotlinx.android.synthetic.main.activity_search.*

class CommunitySearchActivity : AppCompatActivity() {

    private val allData = ArrayList<CommunitySearchAllData>()
    private val cafeData = ArrayList<CommunitySearchAllData>()
    private val userData = ArrayList<CommunitySearchAllData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_search)

        setNavigation()

    }

    private fun setNavigation() {



        tl_act_comm_sear.addTab(tl_act_comm_sear.newTab().setText("전체"))
        tl_act_comm_sear.addTab(tl_act_comm_sear.newTab().setText("카페명"))
        tl_act_comm_sear.addTab(tl_act_comm_sear.newTab().setText("사용자"))

        vp_act_comm_sear.adapter = CommunitySearchPagerAdapter(supportFragmentManager, 3)

        tl_act_comm_sear.setupWithViewPager(vp_act_comm_sear)

        val naviLayout : View = this.layoutInflater.inflate(R.layout.navigation_community_search, null, false)

        tl_act_comm_sear.getTabAt(0)!!.customView = naviLayout.findViewById(R.id.rl_navi_comm_sear_all) as RelativeLayout
        tl_act_comm_sear.getTabAt(1)!!.customView = naviLayout.findViewById(R.id.rl_navi_comm_sear_cafe) as RelativeLayout
        tl_act_comm_sear.getTabAt(2)!!.customView = naviLayout.findViewById(R.id.rl_navi_comm_sear_user) as RelativeLayout


    }

}
