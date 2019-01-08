package com.example.parkseeun.moca_android.ui.location


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide

import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.detail.DetailActivity
import com.example.parkseeun.moca_android.ui.detail.nearbyList.NearbyListData
import com.example.parkseeun.moca_android.ui.location.data.LocationCafeDetailData
import kotlinx.android.synthetic.main.dialog_location_main.*
import org.jetbrains.anko.startActivity

class LocationMainDialog(
    val ctx: Context, val data: LocationCafeDetailData
) : Dialog(ctx) {

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
            ctx.startActivity<DetailActivity>() // index값 보내주기~~
            this.dismiss()
        }

        btn_dialog_location_navi.setOnClickListener {
            val desLat = data.cafe_latitude
            val desLon = data.cafe_longitude
            val gmmIntentUri = Uri.parse("google.navigation:q="+desLat.toString()+","+desLon.toString())
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            ctx.startActivity(mapIntent)
        }


        img_common_cancel_black.setOnClickListener {
            Log.v("플래그 (다이얼로그, 바꾸기 전)", "" + data.selected)
            data.selected = false
            Log.v("플래그 (다이얼로그, 바꾼 후)", "" + data.selected)
            dismiss()
        }
    }
}