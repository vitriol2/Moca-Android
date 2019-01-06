package com.example.parkseeun.moca_android.ui.location


import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log

import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.detail.DetailActivity
import com.example.parkseeun.moca_android.ui.location.data.LocationMainData
import com.example.parkseeun.moca_android.ui.location.data.MarkerItem
import kotlinx.android.synthetic.main.dialog_location_main.*
import org.jetbrains.anko.startActivity

class LocationMainDialog(
    val ctx: Context, val data: LocationMainData,
    val markerLng: ArrayList<MarkerItem>?
) : Dialog(ctx) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_location_main)
        setOnBtnClickListener()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        data.selected = false
        dismiss()
    }

    private fun setOnBtnClickListener() {


//        btn_dialog_location_detail.setOnClickListener {
////            ctx.startActivity<DetailActivity>()
//            this.dismiss()
//        }

        btn_dialog_location_navi.setOnClickListener {
            val gmmIntentUri = Uri.parse("google.navigation:q=37.557538, 127.045322")
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