package com.example.parkseeun.moca_android.ui.category

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_category.*
import android.support.constraint.ConstraintSet
import android.support.v4.view.GravityCompat
import android.widget.TextView
import com.example.parkseeun.moca_android.ui.category.recyclerview.category.ButtonData
import com.example.parkseeun.moca_android.ui.category.recyclerview.category.ButtonViewAdapter
import com.example.parkseeun.moca_android.ui.mypage.NavigationActivity
import org.jetbrains.anko.toast


class CategoryActivity : NavigationActivity(), View.OnClickListener {
    private lateinit var menuViewAdapter : ButtonViewAdapter
    private lateinit var conceptViewAdapter : ButtonViewAdapter
    private var menuList : ArrayList<ButtonData> = ArrayList()
    private var conceptList : ArrayList<ButtonData> = ArrayList()
    private lateinit var regionTvs : ArrayList<TextView>
    private var regionSelected : Int = -1

    override fun onClick(v: View?) {
        when(v){
            cate_menu_iv -> drawer_layout_category.openDrawer(nav_view_category)
            // 다음 버튼
            cate_next_iv -> {
                if(isChecked())
                    Intent(this, CafeListActivity::class.java).apply {
                        putExtra("regionSelected", regionSelected)
                        putExtra("menu", menuList)
                        putExtra("concept", conceptList)
                        startActivity(this)
                    }
                else
                    toast("지역을 선택해주세요")
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
        menuList.add(ButtonData(1, R.drawable.menu_coffee_line, R.drawable.menu_coffee_pink, "커피",false))
        menuList.add(ButtonData(2, R.drawable.menu_tea_line, R.drawable.menu_tea_pink, "차",false))
        menuList.add(ButtonData(3, R.drawable.menu_bakery_line, R.drawable.menu_bakery_pink,"베이커리", false))
        menuList.add(ButtonData(4, R.drawable.menu_fruit_line, R.drawable.menu_fruit_pink, "생과일",false))
        menuList.add(ButtonData(5, R.drawable.menu_dessert_line, R.drawable.menu_dessert_pink, "디저트",false))
        menuList.add(ButtonData(6, R.drawable.menu_etc_line, R.drawable.concept_etc_pink, "기타",false))

        menuViewAdapter = ButtonViewAdapter(this, menuList)
        menuViewAdapter.setOnItemClickListener(this)
        cate_menu_rv.adapter = menuViewAdapter
        cate_menu_rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // concept data
        conceptList.add(ButtonData(1, R.drawable.concept_mood_line, R.drawable.concept_mood_pink, "감성",false))
        conceptList.add(ButtonData(2, R.drawable.concept_pet_line, R.drawable.concept_pet_pink, "펫카페",false))
        conceptList.add(ButtonData(3, R.drawable.concept_hanok_line, R.drawable.concept_hanok_pink, "한옥",false))
        conceptList.add(ButtonData(4, R.drawable.concept_drive_line, R.drawable.concept_drive_pink, "드라이브",false))
        conceptList.add(ButtonData(5, R.drawable.concept_book_line, R.drawable.concept_book_pink, "북카페",false))
        conceptList.add(ButtonData(6, R.drawable.concept_flower_line, R.drawable.concept_flower_pink, "플라워",false))
        conceptList.add(ButtonData(7, R.drawable.concept_rooftop_line, R.drawable.concept_rooftop_pink, "루프탑",false))
        conceptList.add(ButtonData(8, R.drawable.menu_etc_line, R.drawable.concept_etc_pink, "기타",false))

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
        return false
    }
    // 지역 관련 변수 초기화
    private fun initRegion() {
        // 지역 TextView Id
        // 종로구 중구 용산구 성동구 광진구 동대문구 중랑구 성북구 강북구 도봉구 노원구 은평구 서대문구 마포구 양천구 강서구 구로구 금천구 영등포구 동작구 관악구 서초구 강남구 송파구 강동구
        regionTvs = arrayListOf(jongro_tv, midnine_tv, dragon_tv, seongdong_tv, gwangjin_tv, dongdae_tv, midrang_tv, seongbook_tv,
                gangbook_tv, dobong_tv, noone_tv, silver_tv, seodaemoon_tv, mapo_tv, yangcheon_tv, gangseo_tv, guro_tv, goldcheon_tv,
                zeropo_tv, action_tv, pipe_tv, seocho_tv, gangnam_tv, songpa_tv, gangdong_tv)
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
                    constraintSet.connect(R.id.cate_marker_iv, ConstraintSet.START, regionTvs[i].id, ConstraintSet.START)
                    constraintSet.connect(R.id.cate_marker_iv, ConstraintSet.END, regionTvs[i].id, ConstraintSet.END)
                    constraintSet.connect(R.id.cate_marker_iv, ConstraintSet.BOTTOM , regionTvs[i].id, ConstraintSet.BOTTOM)
                    constraintSet.applyTo(cate_map_const)
                    cate_marker_iv.visibility = View.VISIBLE
                }
                setNextColor()
            }
        }
    }
}
