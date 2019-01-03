package com.example.parkseeun.moca_android.ui.location


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Window

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

        data.flag = 0
        dismiss()
    }

    private fun setOnBtnClickListener() {


        btn_dialog_location_detail.setOnClickListener {
            if (ctx is MainActivity) {
//                ctx.showLocationMainActToastMessage()
            }
//            data.flag = 0
//            dismiss()
        }

        img_common_cancel_black.setOnClickListener {
            Log.v("플래그 (다이얼로그, 바꾸기 전)", "" + data.flag)
            data.flag = 0
            Log.v("플래그 (다이얼로그, 바꾼 후)", "" + data.flag)
            dismiss()
        }
    }
}