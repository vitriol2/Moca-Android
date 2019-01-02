package com.example.parkseeun.moca_android.ui.main.coupon

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.widget.TextView
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.fragment_dialog_coupon.*


class CouponDialog2(context: Context, n1: String, n2 : String, n3: String, n4: String) : Dialog(context) {
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.fragment_dialog_coupon)
        setCancelable(false)
        var num1 : TextView= findViewById(R.id.tv_frag_dia_bar_num1) as TextView
        val num2 : TextView= findViewById(R.id.tv_frag_dia_bar_num2) as TextView
        val num3 : TextView= findViewById(R.id.tv_frag_dia_bar_num3) as TextView
        val num4 : TextView= findViewById(R.id.tv_frag_dia_bar_num4) as TextView

        num1.text = n1
        num2.text = n2
        num3.text = n3
        num4.text = n4

        rl_frag_dia_coupon.setOnClickListener {

            dismiss()
        }
    }

}