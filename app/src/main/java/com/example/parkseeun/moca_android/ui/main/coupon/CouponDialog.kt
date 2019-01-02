package com.example.parkseeun.moca_android.ui.main.coupon

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.fragment_dialog_coupon.*


class CouponDialog : DialogFragment() {

    var num1 : String? = null
    var num2 : String? = null
    var num3 : String? = null
    var num4 : String? = null

    companion object {
        private var instance : CouponDialog? = null
        @Synchronized
        fun getInstance(num1 : String, num2 : String, num3 : String, num4: String) : CouponDialog{
            if (instance == null){
                instance = CouponDialog().apply{
                    arguments = Bundle().apply {
                        putString("num1", num1)
                        putString("num2", num2)
                        putString("num3", num3)
                        putString("num4", num4)
                    }
                }
            }
            return instance!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_dialog_coupon, container, false)

        return view

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            num1 = it.getString("num1")
            num2 = it.getString("num2")
            num3 = it.getString("num3")
            num4 = it.getString("num4")

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity is CouponActivity) {
            tv_frag_dia_bar_num1.text = num1
            tv_frag_dia_bar_num2.text = num2
            tv_frag_dia_bar_num3.text = num3
            tv_frag_dia_bar_num4.text = num4
        }
        dismiss()
    }
}
