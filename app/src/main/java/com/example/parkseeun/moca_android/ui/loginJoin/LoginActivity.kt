package com.example.parkseeun.moca_android.ui.loginJoin

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk27.coroutines.textChangedListener
import android.os.Build
import com.example.parkseeun.moca_android.model.post.PostLoginData
import com.example.parkseeun.moca_android.model.post.PostLoginResponse
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.main.HomeActivity2
import com.example.parkseeun.moca_android.util.SharedPreferenceController
import com.example.parkseeun.moca_android.util.User
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    val networkService  = ApplicationController.instance.networkService
    lateinit var postProjResponse : Call<PostLoginResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            window.statusBarColor = Color.parseColor("#e1b2a3")
        }

        // 회원가입 > 버튼
        btn_goToJoin.setOnClickListener {
            val intent : Intent = Intent(this@LoginActivity, JoinActivity::class.java)

            startActivity(intent)
        }

        // EditText에 글 쓰면 동그라미 생기도록,,
        et_login_id.textChangedListener {
            onTextChanged { s, start, before, count ->
                if (et_login_id.text.toString().isNotEmpty()) {
                    iv_id_yellowCircle.visibility = View.VISIBLE
                }
                else {
                    iv_id_yellowCircle.visibility = View.INVISIBLE
                }
            }
        }

        et_login_pw.textChangedListener {
            onTextChanged { s, start, before, count ->
                if (et_login_pw.text.toString().isNotEmpty()) {
                    iv_pw_yellowCircle.visibility = View.VISIBLE
                }
                else {
                    iv_pw_yellowCircle.visibility = View.INVISIBLE
                }
            }
        }

        btn_login.setOnClickListener {
            postProjResponse = networkService.postLogin(PostLoginData(et_login_id.text.toString(),et_login_pw.text.toString()))
            postProjResponse.enqueue(object : Callback<PostLoginResponse> {
                override fun onFailure(call: Call<PostLoginResponse>?, t: Throwable?) {
                    toast(t.toString())
                }

                override fun onResponse(call: Call<PostLoginResponse>?, response: Response<PostLoginResponse>?) {
                    if(response!!.body()!!.status==200) {
                        val token = response.body()!!.data.token!!
                        User.token = token
                        SharedPreferenceController.setAuthorization(this@LoginActivity, token)
                        User.user_id = et_login_id.text.toString()
                        startActivity(Intent(this@LoginActivity, HomeActivity2::class.java))
                    }
                    if(response!!.isSuccessful)
                        if(response!!.body()!!.status==200) {
                            val token = response.body()!!.data.token!!
                            User.token = token
                            SharedPreferenceController.setAuthorization(this@LoginActivity, token)
                            User.token = response.body()!!.data.token!!
                            User.user_id = et_login_id.text.toString()
                            startActivity(Intent(this@LoginActivity, HomeActivity2::class.java))
                        }
                }
            })
        }

        //에뮬돌릴때 로그인 한번 해놓으면 다음에 킬때 바로 홈화면으로, 뒤로가기누르면 LoginActivity로 돌아갈 수 있다.
        if(SharedPreferenceController.getAuthorization(this).isNotEmpty()) {
            startActivity<HomeActivity2>()
        }
    }
}
