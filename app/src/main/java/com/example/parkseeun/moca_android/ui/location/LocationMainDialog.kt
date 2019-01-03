package com.example.parkseeun.moca_android.ui.location


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log

import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.location.data.LocationMainData
import kotlinx.android.synthetic.main.dialog_location_main.*

class LocationMainDialog(val ctx : Context, val data : LocationMainData) : Dialog(ctx) {

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


        btn_dialog_location_detail.setOnClickListener {
            if (ctx is LocationMainActivity) {
//                ctx.showLocationMainActToastMessage()
            }
//            data.flag = 0
//            dismiss()
        }

        img_common_cancel_black.setOnClickListener {
            Log.v("플래그 (다이얼로그, 바꾸기 전)", "" + data.selected)
            data.selected = false
            Log.v("플래그 (다이얼로그, 바꾼 후)", "" + data.selected)
            dismiss()
        }
    }
}