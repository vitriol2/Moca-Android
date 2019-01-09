package com.example.parkseeun.moca_android.ui.location


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import com.bumptech.glide.Glide

import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.detail.DetailActivity
import com.example.parkseeun.moca_android.ui.location.data.LocationCafeDetailData
import kotlinx.android.synthetic.main.dialog_location_main.*
import org.jetbrains.anko.startActivity
import android.widget.Toast
import com.google.android.gms.maps.model.LatLng
//import sun.invoke.util.VerifyAccess.getPackageName




class LocationMainDialog(val ctx: Context, val data: LocationCafeDetailData, val currentLatLng : LatLng?) : Dialog(ctx) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_location_main)
        setOnBtnClickListener()
        setContext()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        data.selected = false
        dismiss()
    }

    private fun setContext(){
        tv_dialog_nearby_cafe_name.text=data.name // 카페이름
        Glide.with(ctx).load(data.cafe_img_url.toString()).into(circle_dialog_nearby_cafe) //이미지
        txt_dialog_nearby_how_close.text= data.distance // 거리
        rating_dialog_location_main.numStars = data.cafe_rating_avg
    }

    private fun setOnBtnClickListener() {

        btn_dialog_location_detail.setOnClickListener {
            val intent : Intent =  Intent(ctx,DetailActivity::class.java)
            intent.putExtra("cafe_id",data.cafe_id)
            ctx.startActivity(intent)
            this.dismiss()


        btn_dialog_location_navi.setOnClickListener {
            if(currentLatLng !=null) {
                val desLat = data.cafe_latitude
                val desLon = data.cafe_longitude
                // 구글맵 연결 (도보, 차량 지원 안함)
                //val gmmIntentUri = Uri.parse("google.navigation:q="+desLat.toString()+","+desLon.toString())
                //  val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                //   mapIntent.setPackage("com.google.android.apps.maps")
                //   ctx.startActivity(mapIntent)
                try {
                    // 다음 맵 연결
                    val daumappIntentUri = Uri.parse(
                        "daummaps://route?sp=" + currentLatLng.latitude.toString() + "," + currentLatLng.longitude.toString() + "&ep="
                                + desLat.toString() + "," + desLon.toString() + "&by=FOOT"
                    )
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = daumappIntentUri
                    ctx.startActivity(intent)
                }catch (anfe: android.content.ActivityNotFoundException) {
                    //플레이스토어 연결
                    Toast.makeText(ctx,"먼저 어플리케이션을 설치해 주세요.",Toast.LENGTH_LONG).show()
                    val playStoreIntentUri= Uri.parse(
                        "market://details?id=net.daum.android.map"
                    )
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = playStoreIntentUri
                    ctx.startActivity(intent)
                    }
            }else {
                Toast.makeText(ctx,"현재 위치가 인식 된 후 이용해주세요.",Toast.LENGTH_LONG).show()
            }
        }


        img_common_cancel_black.setOnClickListener {
            Log.v("플래그 (다이얼로그, 바꾸기 전)", "" + data.selected)
            data.selected = false
            Log.v("플래그 (다이얼로그, 바꾼 후)", "" + data.selected)
            dismiss()
        }
    }
}