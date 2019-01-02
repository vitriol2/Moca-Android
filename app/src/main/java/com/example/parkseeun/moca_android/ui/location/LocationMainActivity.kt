package com.example.parkseeun.moca_android.ui.location

import android.Manifest
import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.decodeResource
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.RelativeLayout
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.location.adapter.LocationMainAdapter
import com.example.parkseeun.moca_android.ui.location.data.LocationMainData
import com.example.parkseeun.moca_android.ui.location.data.MarkerItem
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_location_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import net.daum.mf.map.api.MapView


class LocationMainActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener,GoogleMap.OnMapClickListener, ActivityCompat.OnRequestPermissionsResultCallback {
    private lateinit var selectedMarker: Marker
    private lateinit var mMap: GoogleMap
    private lateinit var mGoogleApiClient: GoogleApiClient
    private lateinit var markerItem: MarkerItem

    // 앱을 실행하기 위해 필요한 퍼미션을 정의합니다.
    internal var REQUIRED_PERMISSIONS =
        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)  // 외부 저장소

    // RecyclerView 설정
    lateinit var locationMainAdapter: LocationMainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_main)

        setRecyclerView()
        //googleMap()
        setOnBtnClickListener()
//        mGoogleApiClient =  GoogleApiClient.Builder(this)
//            .enableAutoManage(this /* FragmentActivity */,
//                this /* OnConnectionFailedListener */)
//            .addConnectionCallbacks(this)
//            .addApi(LocationServices.API)
//            .addApi(Places.GEO_DATA_API)
//            .addApi(Places.PLACE_DETECTION_API)
//            .build()

    }
    fun googleMap() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
//    fun mapView() {
//    var mapView : MapView = MapView(this)
//    mapView.setDaumMapApiKey("cb620091669ce5936f0fd4dd2766a760")
//    val container : RelativeLayout = findViewById(R.id.map_view)
//    container.addView(mapView)
//    }
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

        var markerlist : ArrayList<MarkerItem> = ArrayList()
        markerlist.add(MarkerItem(37.558553039064286, 127.04255064005082))
        markerlist.add(MarkerItem(37.55724150280182, 127.03836384152798))

        // Add a marker in Sydney and move the camera
        val marker1 = LatLng(37.558553039064286, 127.04255064005082)
        val marker2 = LatLng(37.55724150280182, 127.03836384152798)
        val marker3 = LatLng(37.564685851074195, 127.0427905587432)
        val marker4 = LatLng(37.56260260091479, 127.04483008120098)

/*
        for(i in 1..markerlist.size) {
            addMarker(markerItem, false)
        }
*/
        mMap.addMarker(
            MarkerOptions().position(marker1).title("Marker1 in Seoul")
                .icon(
                BitmapDescriptorFactory.fromBitmap(
                    BitmapFactory.decodeResource(resources, R.drawable.location_point)
                )
            )
        )
        mMap.addMarker(
            MarkerOptions().position(marker2).title("Marker2 in Seoul").icon(
                BitmapDescriptorFactory.fromBitmap(
                    BitmapFactory.decodeResource(resources, R.drawable.location_point)
                )
            )
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker1, 14.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)
    }


    override fun onMarkerClick(marker: Marker?): Boolean {
        marker!!.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.location_point_big))
        return false
        /*
        val center = CameraUpdateFactory.newLatLng(marker!!.getPosition())
        mMap.animateCamera(center)

        changeSelectedMarker(marker)
        return true
*/
    }

    override fun onMapClick(p0: LatLng?) {

    }



    private fun addMarker(markerItem: MarkerItem, isSelectedMarker: Boolean): Marker {
        val position = LatLng(markerItem.getLat(), markerItem.getLon())
        val markerOptions = MarkerOptions()
        markerOptions.position(position)

        if (isSelectedMarker) {
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(decodeResource(resources, R.drawable.location_point_big)))
        } else {
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(decodeResource(resources, R.drawable.location_point)))
        }

        return mMap.addMarker(markerOptions)

    }
    /*
    fun addMarkerInMap() {

        for (i in 0..array.count-1) {
            val markerOptions = MarkerOptions()
            var marker : Marker = Marker(markerOptions)
            marker.tag = i
            map.addmarker(marker)
        }


        mMap.setOnMarkerClickListener {
            // 마커를 눌렀을 때 리사이클러뷰의 positino이 마커의 TAG인 것에 변화를 주기
        }
        // 리사이클러뷰 터치 - POSITION얻어옴
        mMap.
*/

    fun showLocationMainActToastMessage() {
        toast("로케이션 메인 엑티비티 함수 호출")
    }

    private fun setOnBtnClickListener() {
        img_common_search.setOnClickListener {
            startActivity<LocationSearchActivity>()
        }
    }

    // RecyclerView 설정
    private fun setRecyclerView() {
// 임시 데이터
        var dataList: ArrayList<LocationMainData> = ArrayList()
        dataList.add(LocationMainData("", "카페1", "1m 이내",false))
        dataList.add(LocationMainData("", "카페2", "10m 이내",false))

        locationMainAdapter = LocationMainAdapter(this, dataList)
        rv_act_location_main.adapter = locationMainAdapter
        rv_act_location_main.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}