package com.example.parkseeun.moca_android.ui.mocapicks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_moca_picks_barista.*

class MocaPicksBaristaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moca_picks_barista)

        ib_moca_picks_back_btn.setOnClickListener {
            finish()
        }
    }
}
