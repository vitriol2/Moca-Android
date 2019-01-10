package com.example.parkseeun.moca_android.ui.communitySearch

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_community_search.*

class CommunitySearchActivity : AppCompatActivity() {

    private val allData = ArrayList<CommunitySearchAllData>()
    private val cafeData = ArrayList<CommunitySearchAllData>()
    private val userData = ArrayList<CommunitySearchAllData>()



    /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_search)

        setNavigation()

    }

    private fun setNavigation() {

        vp_act_comm_sear.adapter = CommunitySearchPagerAdapter(supportFragmentManager, 3)

        tl_act_comm_sear.setupWithViewPager(vp_act_comm_sear)

        val naviLayout : View = this.layoutInflater.inflate(R.layout.navigation_community_search, null, false)

        tl_act_comm_sear.getTabAt(0)!!.customView = naviLayout.findViewById(R.id.rl_navi_comm_sear_all) as RelativeLayout
        tl_act_comm_sear.getTabAt(1)!!.customView = naviLayout.findViewById(R.id.rl_navi_comm_sear_cafe) as RelativeLayout
        tl_act_comm_sear.getTabAt(2)!!.customView = naviLayout.findViewById(R.id.rl_navi_comm_sear_user) as RelativeLayout


    }
    */

    private var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_search)

        viewPager = findViewById(R.id.vp_act_comm_sear) as ViewPager
        setupViewPager(viewPager!!)

        tabLayout = findViewById(R.id.tl_act_comm_sear) as TabLayout
        tabLayout!!.setupWithViewPager(viewPager)
        tabLayout!!.setTabTextColors(Color.parseColor("#e1b2a3"), Color.parseColor("#707070"))
        

        val headerView = (getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.navigation_community_search2, null, false)

        val linearLayoutOne = headerView.findViewById(R.id.ll) as LinearLayout
        val linearLayout2 = headerView.findViewById(R.id.ll2) as LinearLayout
        val linearLayout3 = headerView.findViewById(R.id.ll3) as LinearLayout

        tabLayout!!.getTabAt(0)!!.setCustomView(linearLayoutOne)
        tabLayout!!.getTabAt(1)!!.setCustomView(linearLayout2)
        tabLayout!!.getTabAt(2)!!.setCustomView(linearLayout3)

        setOnBtnClickListeners()

    }

    private fun setOnBtnClickListeners() {
        ib_act_comm_sear_back.setOnClickListener {
            finish()
        }
    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ComSearAllFragment(), "")
        adapter.addFragment(ComSearCafeFragment(), "")
        adapter.addFragment(ComSearUserFragment(), "")
        viewPager.adapter = adapter
    }

    // 통신 (검색 전)
    private fun getBestReviewCafe() {

    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }

}

