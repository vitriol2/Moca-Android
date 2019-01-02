package com.example.parkseeun.moca_android.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_edit_profile.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener

class EditProfileActivity : AppCompatActivity(), KeyboardVisibilityEventListener, TextWatcher {

    override fun onVisibilityChanged(isKeyboardOpen: Boolean) {
         if(isKeyboardOpen) {
            sv_act_edit_profile.scrollTo(0, sv_act_edit_profile.bottom)
         }else{
             sv_act_edit_profile.scrollTo(0, sv_act_edit_profile.top)
         }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        setSupportActionBar(toolbar_edit_profile)

        setBtnSetting()

        setOnBtnClickListeners()
    }

    private fun setOnBtnClickListeners() {
        btn_act_edit_prof_complete.setOnClickListener{
            finish()
        }
    }

    private fun setBtnSetting() {

        btn_act_edit_prof_complete.isEnabled = true

        et_ect_edit_prof_nick.addTextChangedListener(this)
        et_ect_edit_prof_status.addTextChangedListener(this)
        et_ect_edit_prof_phone.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {
         btn_act_edit_prof_complete.isEnabled =
                 et_ect_edit_prof_nick.text.toString().isNotEmpty()&&
                 et_ect_edit_prof_status.text.toString().isNotEmpty()&&
                 et_ect_edit_prof_phone.text.toString().isNotEmpty()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }



}
