package com.example.parkseeun.moca_android.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.example.parkseeun.moca_android.R
import android.view.WindowManager
import com.example.parkseeun.moca_android.ui.loginJoin.LoginActivity
import com.example.parkseeun.moca_android.ui.tutorial.TutorialActivity
import com.example.parkseeun.moca_android.util.SharedPreferenceController


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        Handler().postDelayed({
            if(SharedPreferenceController.getFlag(this)){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }else{
                SharedPreferenceController.setFlag(this, true)
                startActivity(Intent(this, TutorialActivity::class.java))
                finish()
            }
        }, 2900)
    }
}