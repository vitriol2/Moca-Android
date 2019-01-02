package com.example.parkseeun.moca_android.ui.main

<<<<<<< HEAD:app/src/main/java/com/example/parkseeun/moca_android/ui/main/HomeActivity2.kt
import com.example.parkseeun.moca_android.NavigationActivity

=======
import android.content.Intent
import android.view.View
import com.example.parkseeun.moca_android.ui.plus.PlusActivity
import com.example.parkseeun.moca_android.ui.ranking.RankingActivity
import android.support.v7.app.AppCompatActivity
>>>>>>> 1ec80d1f91b3f6c761fe515078c16ea1fb3db561:app/src/main/java/com/example/parkseeun/moca_android/ui/main/HomeActivity.kt
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.*
import android.util.Log
import android.view.Menu

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.SnapHelper
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.main.coupon.CouponActivity
import com.example.parkseeun.moca_android.ui.plus.PlusActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home2.*
import kotlinx.android.synthetic.main.app_bar_home2.*
import org.jetbrains.anko.startActivity

<<<<<<< HEAD:app/src/main/java/com/example/parkseeun/moca_android/ui/main/HomeActivity2.kt
class HomeActivity2 : NavigationActivity(){
=======
class HomeActivity : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{
    private lateinit var mToggle: ActionBarDrawerToggle

    val pickposts: ArrayList<String> = ArrayList()
    val conceptPosts: ArrayList<String> = ArrayList()
    val rankingPosts: ArrayList<CategoryRankData> = ArrayList()
    val plusPosts: ArrayList<String> = ArrayList()

    override fun onClick(v: View?) {
        when(v){
            home_picks_tv -> {
//                to moca picks
            }
            home_concept_tv -> {
//                to category
            }
            home_ranking_tv -> {
                startActivity(Intent(this, RankingActivity::class.java))
            }
            home_plus_tv -> {
                startActivity(Intent(this, PlusActivity::class.java))
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
>>>>>>> 1ec80d1f91b3f6c761fe515078c16ea1fb3db561:app/src/main/java/com/example/parkseeun/moca_android/ui/main/HomeActivity.kt

    private val TAG = "HomeActivity2 onCreate"

<<<<<<< HEAD:app/src/main/java/com/example/parkseeun/moca_android/ui/main/HomeActivity2.kt

    val pickposts: ArrayList<String> = ArrayList()
    val conceptPosts: ArrayList<String> = ArrayList()
    val rankingPosts: ArrayList<CategoryRankData> = ArrayList()
    val plusPosts: ArrayList<String> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
        setSupportActionBar(toolbar)
=======
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        home_picks_tv.setOnClickListener(this)
        home_concept_tv.setOnClickListener(this)
        home_ranking_tv.setOnClickListener(this)
        home_plus_tv.setOnClickListener(this)
>>>>>>> 1ec80d1f91b3f6c761fe515078c16ea1fb3db561:app/src/main/java/com/example/parkseeun/moca_android/ui/main/HomeActivity.kt

        Log.v(TAG, "onCreate")

        makeData()

        recyclerView()

        setHeader(nav_view)

        val snapHelper= LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_act_home_picks)




        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }




    private fun recyclerView() {
        //cafecloud pick
        rv_act_home_picks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_act_home_picks.adapter = CategoryPickAdapter(this, pickposts)

        //concept
        rv_act_home_concept.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_act_home_concept.adapter = CategoryConceptAdapter(this, conceptPosts)

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
            conceptPosts.add("컨셉 $i")

        }
        for (i in 1..3) {
            rankingPosts.add(CategoryRankData("cafe $i", "location $i"))
            plusPosts.add("image $i")

        }
    }
}
