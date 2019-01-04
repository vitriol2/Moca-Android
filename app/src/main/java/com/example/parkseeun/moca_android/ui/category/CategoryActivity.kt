package com.example.parkseeun.moca_android.ui.category

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_category.*
import android.support.constraint.ConstraintSet
import android.support.v4.view.GravityCompat
import android.widget.TextView
import com.example.parkseeun.moca_android.util.NavigationActivity


class CategoryActivity : NavigationActivity(), View.OnClickListener {
    private lateinit var menuViewAdapter : ButtonViewAdapter
    private lateinit var conceptViewAdapter : ButtonViewAdapter
    private var menuList : ArrayList<ButtonData> = ArrayList()
    private var conceptList : ArrayList<ButtonData> = ArrayList()
    private lateinit var regionIds : ArrayList<Int>
    private lateinit var regionTvs : ArrayList<TextView>
    private var regionSelected : Int = -1

    override fun onClick(v: View?) {
        when(v){
            cate_menu_iv -> drawer_layout_category.openDrawer(nav_view_category)
            // 다음 버튼
            cate_next_iv -> {
//                if(isChecked())
//                    Intent(this, )
            }
            // 지도, 컨셉, 메뉴
            else -> {
                // 누르면 flag 상태 토글. rv 업데이트
                // 메뉴
                if (cate_menu_rv.indexOfChild(v!!) != -1) {
                    menuList[cate_menu_rv.getChildAdapterPosition(v!!)].flag = !menuList[cate_menu_rv.getChildAdapterPosition(v!!)].flag
                    menuViewAdapter.notifyDataSetChanged()
                // 컨셉
                } else if (cate_concept_rv.indexOfChild(v!!) != -1){
                    conceptList[cate_concept_rv.getChildAdapterPosition(v!!)].flag = !conceptList[cate_concept_rv.getChildAdapterPosition(v!!)].flag
                    conceptViewAdapter.notifyDataSetChanged()
                }
            }
        }
        setNextColor()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        setHeader(nav_view_category)

        initRegion()
        setClickListener()

        cate_menu_iv.setOnClickListener(this)
        cate_next_iv.setOnClickListener(this)

        setRecyclerView()
    }

    override fun onBackPressed() {
        if (drawer_layout_category.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_category.closeDrawer(GravityCompat.START)
        } else {
            finishAfterTransition()
        }
    }

    // RecyclerView 설정
    private fun setRecyclerView() {
        // menu data
        menuList.add(ButtonData(R.drawable.menu_coffee_line, R.drawable.menu_coffee_pink, false))
        menuList.add(ButtonData(R.drawable.menu_tea_line, R.drawable.menu_tea_pink, false))
        menuList.add(ButtonData(R.drawable.menu_bakery_line, R.drawable.menu_bakery_pink, false))
        menuList.add(ButtonData(R.drawable.menu_fruit_line, R.drawable.menu_fruit_pink, false))
        menuList.add(ButtonData(R.drawable.menu_dessert_line, R.drawable.menu_dessert_pink, false))
        menuList.add(ButtonData(R.drawable.menu_etc_line, R.drawable.concept_etc_pink, false))

        menuViewAdapter = ButtonViewAdapter(this, menuList)
        menuViewAdapter.setOnItemClickListener(this)
        cate_menu_rv.adapter = menuViewAdapter
        cate_menu_rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // concept data
        conceptList.add(ButtonData(R.drawable.concept_mood_line, R.drawable.concept_mood_pink, false))
        conceptList.add(ButtonData(R.drawable.concept_pet_line, R.drawable.concept_pet_pink, false))
        conceptList.add(ButtonData(R.drawable.concept_hanok_line, R.drawable.concept_hanok_pink, false))
        conceptList.add(ButtonData(R.drawable.concept_drive_line, R.drawable.concept_drive_pink, false))
        conceptList.add(ButtonData(R.drawable.concept_book_line, R.drawable.concept_book_pink, false))
        conceptList.add(ButtonData(R.drawable.concept_flower_line, R.drawable.concept_flower_pink, false))
        conceptList.add(ButtonData(R.drawable.concept_rooftop_line, R.drawable.concept_rooftop_pink, false))
        conceptList.add(ButtonData(R.drawable.menu_etc_line, R.drawable.concept_etc_pink, false))

        conceptViewAdapter = ButtonViewAdapter(this, conceptList)
        conceptViewAdapter.setOnItemClickListener(this)
        cate_concept_rv.adapter = conceptViewAdapter
        cate_concept_rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setNextColor() {
        if(isChecked())
            cate_next_iv.setTextColor(resources.getColor(R.color.colorPrimary))
        else
            cate_next_iv.setTextColor(resources.getColor(R.color.dark_gray))
    }
    // 카테고리 선택했는지 알려주는 함수
    private fun isChecked(): Boolean {
        // 지도 체크
        if(regionSelected != -1)
            return true
        // 메뉴 체크
        for(item in menuList)
            if(item.flag)
                return true
        // 컨셉 체크
        for(item in conceptList)
            if(item.flag)
                return true
        return false
    }
    // 지역 관련 변수 초기화
    private fun initRegion() {
        // 지역 TextView Id
        // 강서 구로 양천 금천 영등포 마포 은평 서대문 관악 동작 종로 용산 중구 서초 강북 성북 도봉 성동 동대문 강남 노원 광진 중랑 송파 강동
        regionIds = arrayListOf(R.id.gangseo_tv, R.id.guro_tv, R.id.yangcheon_tv, R.id.goldcheon_tv, R.id.zeropo_tv, R.id.mapo_tv, R.id.silver_tv, R.id.seodaemoon_tv,
                R.id.pipe_tv, R.id.action_tv, R.id.jongro_tv, R.id.dragon_tv, R.id.midnine_tv, R.id.seocho_tv, R.id.gangbook_tv,
                R.id.seongbook_tv, R.id.dobong_tv, R.id.seongdong_tv, R.id.dongdae_tv, R.id.gangnam_tv, R.id.noone_tv, R.id.gwangjin_tv,
                R.id.midrang_tv, R.id.songpa_tv, R.id.gangdong_tv)
        regionTvs = arrayListOf(gangseo_tv, guro_tv, yangcheon_tv, goldcheon_tv, zeropo_tv, mapo_tv, silver_tv, seodaemoon_tv,
                pipe_tv, action_tv, jongro_tv, dragon_tv, midnine_tv, seocho_tv, gangbook_tv,
                seongbook_tv, dobong_tv, seongdong_tv, dongdae_tv, gangnam_tv, noone_tv, gwangjin_tv,
                midrang_tv, songpa_tv, gangdong_tv)
    }
    // 지역 TextView에 clickListener 달기
    private fun setClickListener() {
        for(i in 0 until regionTvs.size){
            regionTvs[i].setOnClickListener {
                // 이전에 선택한 지역이 클릭한 지역일 때
                if(regionSelected != -1 && regionSelected == i) {
                    regionSelected = -1
                    cate_marker_iv.visibility = View.GONE
                }else{
                    // 이전에 선택한 지역과 다른 지역 혹은 처음으로 지역을 선택할 때
                    regionSelected = i
                    // 마커 위치 설정: 선택한 지역의 id를 기준으로 설정
                    val constraintSet = ConstraintSet()
                    constraintSet.clone(cate_map_const)
                    constraintSet.connect(R.id.cate_marker_iv, ConstraintSet.START, regionIds[i], ConstraintSet.START)
                    constraintSet.connect(R.id.cate_marker_iv, ConstraintSet.END, regionIds[i], ConstraintSet.END)
                    constraintSet.connect(R.id.cate_marker_iv, ConstraintSet.BOTTOM , regionIds[i], ConstraintSet.BOTTOM)
                    constraintSet.applyTo(cate_map_const)
                    cate_marker_iv.visibility = View.VISIBLE
                }
                setNextColor()
            }
        }
    }
}
