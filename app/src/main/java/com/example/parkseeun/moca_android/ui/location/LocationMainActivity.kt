package com.example.parkseeun.moca_android.ui.location

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.location.adapter.LocationMainAdapter
import com.example.parkseeun.moca_android.ui.location.data.LocationMainData
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_location_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LocationMainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    // RecyclerView 설정
    lateinit var locationMainAdapter: LocationMainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_main)

        setRecyclerView()
      googleMap()
        setOnBtnClickListener()

    }

  fun googleMap(){
      val mapFragment = supportFragmentManager
          .findFragmentById(R.id.map) as SupportMapFragment
      mapFragment.getMapAsync(this)
  }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
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