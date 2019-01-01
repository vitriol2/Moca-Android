package com.example.parkseeun.moca_android.ui.main.coupon

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.example.parkseeun.moca_android.R


class CouponDialog(val ctx : Context) : AlertDialog(ctx) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_dialog_barcode)

        setBtnClickListener()
    }

    private fun setBtnClickListener() {

    }
}