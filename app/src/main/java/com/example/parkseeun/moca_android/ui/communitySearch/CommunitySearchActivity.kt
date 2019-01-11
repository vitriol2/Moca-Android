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
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetCommunitySearchListResponse
import com.example.parkseeun.moca_android.model.get.ReviewData
import com.example.parkseeun.moca_android.model.get.SearchUserData
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.detail.detailReviewAll.ReviewAllPopularAdapter
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_community_search.*
import kotlinx.android.synthetic.main.fragment_com_sear_all.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunitySearchActivity : AppCompatActivity() {

    private val allData = ArrayList<CommunitySearchAllData>()
    private val cafeData = ArrayList<CommunitySearchAllData>()
    private val userData = ArrayList<CommunitySearchAllData>()

    // 통신
    private val networkService  = ApplicationController.instance.networkService
    // 전체
    private lateinit var getCommunitySearchListResponse : Call<GetCommunitySearchListResponse>
    private var popularReviewList = ArrayList<ReviewData>()
    private var reviewListOrderByLatest = ArrayList<ReviewData>()
    private var searchUserList = ArrayList<SearchUserData>()

    private var view = View(this)

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



        viewPager = findViewById(R.id.vp_act_comm_sear)
        setupViewPager(viewPager!!)

        tabLayout = findViewById(R.id.tl_act_comm_sear)
        tabLayout!!.setupWithViewPager(viewPager)
        tabLayout!!.setTabTextColors(Color.parseColor("#e1b2a3"), Color.parseColor("#707070"))


        setScreenConvert()

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

    private fun setScreenConvert() {
        et_act_comm_sear.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val LlNothing : LinearLayout = findViewById(R.id.ll_frag_com_sear_all_nothing)

                if (s.toString() == "") {
                    LlNothing.visibility = View.VISIBLE
                }
                else {
                    LlNothing.visibility = View.GONE
                    getAllResult(s.toString())
                }
            }
        })
    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        val bundle = Bundle()
        bundle.putParcelableArrayList("popularReviewList", popularReviewList)
        bundle.putParcelableArrayList("searchUserList", searchUserList)
        val fragobj = ComSearAllFragment()
        fragobj.setArguments(bundle)
        adapter.addFragment(fragobj, "")

        val bundle2 = Bundle()
        bundle2.putParcelableArrayList("popularReviewList", popularReviewList)
        bundle2.putParcelableArrayList("reviewListOrderByLatest", reviewListOrderByLatest)
        val fragobj2 = ComSearCafeFragment()
        fragobj2.setArguments(bundle2)
        adapter.addFragment(fragobj2, "")

        val bundle3 = Bundle()
        bundle3.putParcelableArrayList("popularReviewList", popularReviewList)
        bundle3.putParcelableArrayList("reviewListOrderByLatest", reviewListOrderByLatest)
        val fragobj3 = ComSearUserFragment()
        fragobj3.setArguments(bundle3)
        adapter.addFragment(fragobj3, "")
        viewPager.adapter = adapter
    }

    // 통신 (전체 탭)
    private fun getAllResult(searchString : String) {
        getCommunitySearchListResponse = networkService.getCommunitySearchResult(User.token!!, searchString)
        getCommunitySearchListResponse.enqueue(object : Callback<GetCommunitySearchListResponse> {
            override fun onFailure(call: Call<GetCommunitySearchListResponse>, t: Throwable) {
                toast(t.message.toString())
            }

            override fun onResponse(call: Call<GetCommunitySearchListResponse>, response: Response<GetCommunitySearchListResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        var communitySearchData = response.body()!!.data

                        popularReviewList = communitySearchData.popularReviewList
                        reviewListOrderByLatest = communitySearchData.reviewListOrderByLatest
                        searchUserList = communitySearchData.searchUserList

                        // RecyclerView 설정
                        rv_frag_com_sear_all_review.layoutManager = LinearLayoutManager(this@CommunitySearchActivity, LinearLayoutManager.HORIZONTAL, false)
                        rv_frag_com_sear_all_review.adapter = ReviewAllPopularAdapter(this@CommunitySearchActivity, popularReviewList)

                        rv_frag_com_sear_all_user.layoutManager = LinearLayoutManager(this@CommunitySearchActivity)
                        rv_frag_com_sear_all_user.adapter = ComSearUserAdapter(this@CommunitySearchActivity, searchUserList)
                    }
                }
                else {
                    toast(response.body()!!.status.toString() + " : " +response.body()!!.message.toString())
                    toast("123")
                }
            }
        })
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
