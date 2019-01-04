package com.example.parkseeun.moca_android.ui.category

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var menuViewAdapter : ButtonViewAdapter
    private lateinit var conceptViewAdapter : ButtonViewAdapter
    private var menuList : ArrayList<ButtonData> = ArrayList()
    private var conceptList : ArrayList<ButtonData> = ArrayList()

    override fun onClick(v: View?) {
        when(v){
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
        if(isChecked())
            cate_next_iv.setTextColor(resources.getColor(R.color.colorPrimary))
        else
            cate_next_iv.setTextColor(resources.getColor(R.color.dark_gray))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        cate_next_iv.setOnClickListener(this)

        setRecyclerView()
    }

    // RecyclerView 설정
    private fun setRecyclerView() {
        // menu data
        menuList.add(ButtonData(R.drawable.menu_coffee_line, R.drawable.menu_coffee_pink, false))
        menuList.add(ButtonData(R.drawable.menu_tea_line, R.drawable.menu_tea_pink, false))
        menuList.add(ButtonData(R.drawable.menu_bakery_line, R.drawable.menu_bakery_pink, false))
        menuList.add(ButtonData(R.drawable.menu_fruit_line, R.drawable.menu_fruit_pink, false))
        menuList.add(ButtonData(R.drawable.menu_dessert_line, R.drawable.menu_dessert_pink, false))
        menuList.add(ButtonData(R.drawable.menu_etc_line, R.drawable.menu_etc_pink, false))

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
        conceptList.add(ButtonData(R.drawable.concept_etc_line, R.drawable.concept_etc_pink, false))

        conceptViewAdapter = ButtonViewAdapter(this, conceptList)
        conceptViewAdapter.setOnItemClickListener(this)
        cate_concept_rv.adapter = conceptViewAdapter
        cate_concept_rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    // 카테고리 선택했는지 알려주는 함수
    private fun isChecked(): Boolean {
        // 지도 체크
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
}
