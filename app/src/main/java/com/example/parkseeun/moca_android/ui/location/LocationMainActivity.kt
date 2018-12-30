package com.example.parkseeun.moca_android.ui.location

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.R.id.container
import com.example.parkseeun.moca_android.ui.location.adapter.LocationMainAdapter
import com.example.parkseeun.moca_android.ui.location.data.LocationMainData
import kotlinx.android.synthetic.main.activity_location_main.*
import net.daum.mf.map.api.MapView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LocationMainActivity : AppCompatActivity() {

    // RecyclerView 설정
    lateinit var locationMainAdapter: LocationMainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_main)
        mapView()
        setRecyclerView()
        setOnBtnClickListener()

    }

    fun mapView() {
        var mapView : MapView = MapView(this)
        mapView.setDaumMapApiKey("cb620091669ce5936f0fd4dd2766a760")
        val container : RelativeLayout = findViewById(R.id.map_view)
        container.addView(mapView)
    }

    fun showLocationMainActToastMessage() {
        toast("로케이션 메인 엑티비티 함수 호출")
    }

    private fun setOnBtnClickListener() {
        img_location_main_hamberger.setOnClickListener {
            startActivity<LocationSearchActivity>()
        }
    }

    // RecyclerView 설정
    private fun setRecyclerView() {

// 임시 데이터
        var dataList: ArrayList<LocationMainData> = ArrayList()
        dataList.add(LocationMainData("", "카페1", "1m 이내"))
        dataList.add(LocationMainData("", "카페2", "10m 이내"))

        locationMainAdapter = LocationMainAdapter(this, dataList)
        rv_act_location_main.adapter = locationMainAdapter
        rv_act_location_main.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }
}