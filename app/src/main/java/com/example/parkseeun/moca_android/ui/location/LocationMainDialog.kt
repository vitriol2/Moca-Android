package com.example.parkseeun.moca_android.ui.location


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Window

import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.dialog_location_main.*

class LocationMainDialog(val ctx : Context) : Dialog(ctx) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_location_main)
        setOnBtnClickListener()
    }

    private fun setOnBtnClickListener() {
        btn_dialog_location_detail.setOnClickListener {
            if (ctx is LocationMainActivity) {
                ctx.showLocationMainActToastMessage()
            }
            dismiss()
        }
    }
}