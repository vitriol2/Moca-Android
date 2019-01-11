package com.example.parkseeun.moca_android.ui.tutorial

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.loginJoin.LoginActivity
import kotlinx.android.synthetic.main.fragment_tutorial4.view.*
import org.jetbrains.anko.support.v4.startActivity

class Tutorial4Fragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_tutorial4, container, false)
        v.tutorial4_start_tv.setOnClickListener {
            startActivity<LoginActivity>()
            activity!!.finish()
        }
        return v
    }
}