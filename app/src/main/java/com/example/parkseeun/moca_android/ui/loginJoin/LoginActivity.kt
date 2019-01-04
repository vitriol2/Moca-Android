package com.example.parkseeun.moca_android.ui.loginJoin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk27.coroutines.textChangedListener

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
    }
}
