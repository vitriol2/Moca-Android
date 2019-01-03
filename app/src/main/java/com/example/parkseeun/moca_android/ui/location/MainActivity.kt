package com.example.parkseeun.moca_android.ui.location


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast

import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.location.adapter.LocationMainAdapter
import com.example.parkseeun.moca_android.ui.location.data.LocationMainData
import com.example.parkseeun.moca_android.ui.location.data.MarkerItem
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_location_main.*

import java.io.IOException
import java.util.Locale


class MainActivity : AppCompatActivity(), OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback,
    GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

    // RecyclerView 설정
    lateinit var locationMainAdapter: LocationMainAdapter
    private var mMap: GoogleMap? = null
    //private var currentMarker: Marker? = null
    internal var needRequest = false
    private var flag: Boolean = false
    private var markerflag: Boolean = false
    private var mSelectedMarker: Marker? = null
    var markerlist: ArrayList<MarkerItem> = ArrayList()


    // 앱을 실행하기 위해 필요한 퍼미션을 정의합니다.
    internal var REQUIRED_PERMISSIONS =
        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)  // 외부 저장소


    internal var mCurrentLocatiion: Location? = null
    internal var currentPosition: LatLng? = null


    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var locationRequest: LocationRequest? = null
    private var location: Location? = null


    private var mLayout: View? = null  // Snackbar 사용하기 위해서는 View가 필요합니다.


    internal var locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)

            val locationList = locationResult!!.locations

            if (locationList.size > 0) {//locationResult에서 얻어온 location이 있을 때
                location = locationList[locationList.size - 1] // location은 locationList 배열의 마지막! (제일 최근위치(?))
                //location = locationList.get(0);

                currentPosition = LatLng(location!!.latitude, location!!.longitude) // 최근위치.


                val markerTitle = getCurrentAddress(currentPosition!!) //뒤에 있는 함수 (currentPosition 최근위치를 보냄)
                val markerSnippet = ("위도:" + location!!.latitude.toString()
                        + " 경도:" + location!!.longitude.toString()) //현재위치의 위도와 경도를 나타내는 말풍선

                Log.d(TAG, "onLocationResult : $markerSnippet")


                //현재 위치에 마커 생성하고 이동
                setCurrentLocation(location, markerTitle, markerSnippet)

                mCurrentLocatiion = location
            }
        }

    }
    // (참고로 Toast에서는 Context가 필요했습니다.)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_main)
        setOnBtnClickListener()
        recyclerconnect()
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
//            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//        )
        mLayout = findViewById(R.id.rl_location_main_toolbar)
        Log.d(TAG, "onCreate")

        locationRequest = LocationRequest()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY) //create a location request
            .setInterval(UPDATE_INTERVAL_MS.toLong()) //이 메소드는 앱에서 위치 업데이트 수신간격을 밀리초단위로 설정한다.
        //     .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS.toLong()) //이 메소드는 앱에서 위치 업데이트를 가장 빠르게 처리할 수 있도록 밀리초 단위로 설정한다.


        val builder = LocationSettingsRequest.Builder()

        builder.addLocationRequest(locationRequest!!)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager // 프래그먼트에 구글 맵 띄우기
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        setRecyclerView()
    }

    private fun recyclerconnect() {
//
//        for (i in 0..markerlist.size) {
//            if (markerlist[i].getmarker() == true) { //리사이클러뷰가 눌리면 그 마커에 포커스..
//                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.location_point_big))
//                val selectedmarkerLatLng =
//                    CameraUpdateFactory.newLatLng(LatLng(markerlist.get(i).lat, markerlist.get(i).lon))
//                mMap!!.moveCamera(selectedmarkerLatLng)
////}
//            }
//        }
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        // if (null != mSelectedMarker) {
        //   mSelectedMarker!!.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.location_point_big))
        //}
        mSelectedMarker = marker
        markerflag = true
        for (i in 0..markerlist.size) {
//            if(markerlist[i].lat==marker!!.position.latitude && markerlist[i].lon==marker.position.longitude){

            //}
        }

        if (markerflag == true) {
            mSelectedMarker!!.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.location_point_big))
        }
        //mSelectedMarker!!.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.location_point))
        return false
    }

    override fun onMapClick(latLng: LatLng) {
        if (markerflag == false) {
            mSelectedMarker!!.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.location_point))
        }
        mSelectedMarker = null
    }

    private fun setRecyclerView() {
// 임시 데이터
        var dataList: ArrayList<LocationMainData> = ArrayList()
        dataList.add(LocationMainData("", "카페1", "1m 이내", 0))
        dataList.add(LocationMainData("", "카페2", "10m 이내", 0))

        locationMainAdapter = LocationMainAdapter(this, dataList, markerlist)
        rv_act_location_main.adapter = locationMainAdapter
        rv_act_location_main.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setOnBtnClickListener() {

    }

    private fun startLocationUpdates() {
        if (!checkLocationServicesStatus()) { //gps 꺼져있으면
            Log.d(TAG, "startLocationUpdates : call showDialogForLocationServiceSetting")
            showDialogForLocationServiceSetting() //설정에서 gps켤 수 있도록 다이얼로그를 띄움
        } else { //gps 켜져있으면
            val hasFineLocationPermission = ContextCompat.checkSelfPermission( //geo 권한설정 1
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(//geo 권한설정 2
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED || hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "startLocationUpdates : 퍼미션 안가지고 있음")
                return
            }
            Log.d(TAG, "startLocationUpdates : call mFusedLocationClient.requestLocationUpdates")
            mFusedLocationClient!!.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
            if (checkPermission())
                mMap!!.isMyLocationEnabled = true
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        Log.d(TAG, "onMapReady :")

        mMap = googleMap

        markerlist.add(MarkerItem(37.558553039064286, 127.04255064005082, 0, false))
        markerlist.add(MarkerItem(37.55724150280182, 127.03836384152798, 0, false))
        markerlist.add(MarkerItem(37.564685851074195, 127.0427905587432, 0, false))
        markerlist.add(MarkerItem(37.56260260091479, 127.04483008120098, 0, false))

        for (i in markerlist.indices) {
            mMap!!.addMarker(
                MarkerOptions().position(
                    LatLng(
                        markerlist.get(i).lat,
                        markerlist.get(i).lon
                    )
                ).title("Marker" + (i + 1) + " in Seoul")
                    .icon(
                        BitmapDescriptorFactory.fromBitmap(
                            BitmapFactory.decodeResource(resources, R.drawable.location_point)
                        )
                    )
            )
            markerlist[i].setPosition(i)
        }

        //런타임 퍼미션 요청 대화상자나 GPS 활성 요청 대화상자 보이기전에
        //지도의 초기위치를 서울로 이동
        setDefaultLocation()


        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )



        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            startLocationUpdates() // 3. 위치 업데이트 시작


        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Snackbar.make(
                    mLayout!!, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("확인") {
                    // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                    ActivityCompat.requestPermissions(
                        this@MainActivity, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE
                    )
                }.show()


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(
                    this, REQUIRED_PERMISSIONS,
                    PERMISSIONS_REQUEST_CODE
                )
            }

        }

        mMap!!.uiSettings.isMyLocationButtonEnabled = false
        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(15f))
        mMap!!.setOnMapClickListener { Log.d(TAG, "onMapClick :") }
    }

    override fun onStart() {
        super.onStart()

        Log.d(TAG, "onStart")

        if (checkPermission()) {

            Log.d(TAG, "onStart : call mFusedLocationClient.requestLocationUpdates")
            mFusedLocationClient!!.requestLocationUpdates(locationRequest, locationCallback, null)

            if (mMap != null)
                mMap!!.isMyLocationEnabled = true // gps 사용가능

        }


    }


    override fun onStop() {

        super.onStop()

        if (mFusedLocationClient != null) {
            Log.d(TAG, "onStop : call stopLocationUpdates")
            mFusedLocationClient!!.removeLocationUpdates(locationCallback)
        }
    }


    fun getCurrentAddress(latlng: LatLng): String {

        //지오코더... GPS를 주소로 변환
        val geocoder = Geocoder(this, Locale.getDefault())

        val addresses: List<Address>?

        try {

            addresses = geocoder.getFromLocation(
                latlng.latitude,
                latlng.longitude,
                1
            )
        } catch (ioException: IOException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show()
            return "지오코더 서비스 사용불가"
        } catch (illegalArgumentException: IllegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show()
            return "잘못된 GPS 좌표"

        }
        if (addresses == null || addresses.size == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show()
            return "주소 미발견"
        } else {
            val address = addresses[0]
            return address.getAddressLine(0).toString()
        }
    }

    fun checkLocationServicesStatus(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    fun setCurrentLocation(location: Location?, markerTitle: String, markerSnippet: String) {
        //if (currentMarker != null) currentMarker!!.remove()
        val currentLatLng = LatLng(location!!.latitude, location.longitude)

        val markerOptions = MarkerOptions()
        markerOptions.position(currentLatLng)
        markerOptions.title(markerTitle)
        markerOptions.snippet(markerSnippet)
        markerOptions.draggable(true)

        // currentMarker = mMap!!.addMarker(markerOptions)
        if (flag == false) { //실행 처음에만 자신의 위치로 포커스
            val cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng)
            mMap!!.moveCamera(cameraUpdate)
            flag = true
        }

        img_mylocation_btn.setOnClickListener {
            val cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng)
            mMap!!.moveCamera(cameraUpdate)
            flag = true
        }
    }


    fun setDefaultLocation() { // gps꺼둔 상태일 때
        //디폴트 위치, Seoul
        val DEFAULT_LOCATION = LatLng(37.56, 126.97)
        val markerTitle = "위치정보 가져올 수 없음"
        val markerSnippet = "위치 퍼미션과 GPS 활성 여부 확인하세요"

        //    if (currentMarker != null) currentMarker!!.remove()

        var markerOptions = MarkerOptions()
        markerOptions.position(DEFAULT_LOCATION)
        markerOptions.title(markerTitle)
        markerOptions.snippet(markerSnippet)
        markerOptions.draggable(true)
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        //currentMarker = mMap!!.addMarker(markerOptions)

        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 15f)
        mMap!!.moveCamera(cameraUpdate)
    }

    //여기부터는 런타임 퍼미션 처리을 위한 메소드들
    private fun checkPermission(): Boolean {

        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        return if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
            true
        } else false
//true면 퍼미션 허용한거  !
    }

    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    override fun onRequestPermissionsResult(
        permsRequestCode: Int,
        permissions: Array<String>,
        grandResults: IntArray
    ) {
        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.size == REQUIRED_PERMISSIONS.size) {
            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면
            var check_result = true
            // 모든 퍼미션을 허용했는지 체크합니다.
            for (result in grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false
                    break
                }
            }
            if (check_result) {
                // 퍼미션을 허용했다면 위치 업데이트를 시작합니다.
                startLocationUpdates()
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        REQUIRED_PERMISSIONS[0]
                    ) || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])
                ) {
                    // 사용자가 거부만 선택한 경우에는 앱을 다시 실행하여 허용을 선택하면 앱을 사용할 수 있습니다.
                    Snackbar.make(
                        mLayout!!, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요. ",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("확인") { finish() }.show()
                } else {
                    // "다시 묻지 않음"을 사용자가 체크하고 거부를 선택한 경우에는 설정(앱 정보)에서 퍼미션을 허용해야 앱을 사용할 수 있습니다.
                    Snackbar.make(
                        mLayout!!, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("확인") { finish() }.show()
                }
            }

        }
    }

    //여기부터는 GPS 활성화를 위해 다이얼로그 띄우기
    private fun showDialogForLocationServiceSetting() {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("위치 서비스 비활성화")
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n" + "위치 설정을 수정하실래요?")
        builder.setCancelable(true)
        builder.setPositiveButton("설정") { dialog, id ->
            val callGPSSettingIntent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS) //gps설정으로 감
            startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE)
        }
        builder.setNegativeButton("취소") { dialog, id -> dialog.cancel() }
        builder.create().show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GPS_ENABLE_REQUEST_CODE ->
                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {
                        Log.d(TAG, "onActivityResult : GPS 활성화 되어있음")
                        needRequest = true
                        return
                    }
                }
        }
    }

    companion object {
        private val TAG = "LocationMainActivity "
        private val GPS_ENABLE_REQUEST_CODE = 2001
        private val UPDATE_INTERVAL_MS = 1000  // 1초
        //  private val FASTEST_UPDATE_INTERVAL_MS = 500 // 0.5초마다 현재위치로 이동


        // onRequestPermissionsResult에서 수신된 결과에서 ActivityCompat.requestPermissions를 사용한 퍼미션 요청을 구별하기 위해 사용됩니다.
        private val PERMISSIONS_REQUEST_CODE = 100
    }


}