package com.example.parkseeun.moca_android.ui.loginJoin

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk27.coroutines.textChangedListener
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import com.example.parkseeun.moca_android.model.post.PostLoginData
import com.example.parkseeun.moca_android.model.post.PostLoginResponse
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.main.HomeActivity
import com.example.parkseeun.moca_android.util.SharedPreferenceController
import com.example.parkseeun.moca_android.util.User
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity(), KeyboardVisibilityEventListener, TextWatcher {

    val networkService = ApplicationController.instance.networkService
    lateinit var postProjResponse: Call<PostLoginResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        KeyboardVisibilityEvent.setEventListener(this, this)

        if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            window.statusBarColor = Color.parseColor("#e1b2a3")
        }

        // 회원가입 > 버튼
        btn_goToJoin.setOnClickListener {
            val intent: Intent = Intent(this@LoginActivity, JoinActivity::class.java)

            startActivity(intent)
        }

        // EditText에 글 쓰면 동그라미 생기도록,,
        et_login_id.textChangedListener {
            onTextChanged { s, start, before, count ->
                if (et_login_id.text.toString().isNotEmpty()) {
                    iv_id_yellowCircle.visibility = View.VISIBLE
                } else {
                    iv_id_yellowCircle.visibility = View.INVISIBLE
                }
            }
        }

        et_login_pw.textChangedListener {
            onTextChanged { s, start, before, count ->
                if (et_login_pw.text.toString().isNotEmpty()) {
                    iv_pw_yellowCircle.visibility = View.VISIBLE
                } else {
                    iv_pw_yellowCircle.visibility = View.INVISIBLE
                }
            }
        }
        btn_login.isEnabled = false
        et_login_id.addTextChangedListener(this)
        et_login_pw.addTextChangedListener(this)

        btn_login.setOnClickListener {
            postProjResponse =
                    networkService.postLogin(PostLoginData(et_login_id.text.toString(), et_login_pw.text.toString()))
            postProjResponse.enqueue(object : Callback<PostLoginResponse> {
                override fun onFailure(call: Call<PostLoginResponse>?, t: Throwable?) {
                    toast(t.toString())
                }

                override fun onResponse(call: Call<PostLoginResponse>?, response: Response<PostLoginResponse>?) {
                    if (response!!.isSuccessful)
                        if (response!!.body()!!.status == 200) {
                            val token = response.body()!!.data.token!!
                            User.token = token
                            SharedPreferenceController.setAuthorization(this@LoginActivity, token)
                            SharedPreferenceController.setId(this@LoginActivity, et_login_id.text.toString())
                            SharedPreferenceController.setPw(this@LoginActivity, et_login_pw.text.toString())
                            User.token = response.body()!!.data.token!!
                            User.user_id = et_login_id.text.toString()
                            User.user_password = et_login_pw.text.toString()
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                            finish()
                        }
                }
            })
        }

        //에뮬돌릴때 로그인 한번 해놓으면 다음에 킬때 바로 홈화면으로, 뒤로가기누르면 LoginActivity로 돌아갈 수 있다.
        if (SharedPreferenceController.getAuthorization(this).isNotEmpty()) {
            startActivity<HomeActivity>()
            finish()
            User.token = SharedPreferenceController.getAuthorization(this)
            User.user_id = SharedPreferenceController.getId(this)
            User.user_password = SharedPreferenceController.getPw(this)
        }
    }

    override fun onVisibilityChanged(isOpen: Boolean) {
        if (isOpen) {
            scroll_view.scrollTo(0, scroll_view.bottom)
            btn_goToJoin.visibility = View.GONE
        } else
            scroll_view.scrollTo(0, scroll_view.top)
        btn_goToJoin.visibility = View.GONE
    }

    override fun afterTextChanged(s: Editable?) {
        btn_login.isEnabled = et_login_id.text.isNotEmpty() && et_login_pw.text.isNotEmpty()

    }


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }


}

