package com.example.parkseeun.moca_android.util

import android.content.Context

object SharedPreferenceController {

    private val USER_NAME = "MYKEY"
    private val myAuth = "myAuth"

    fun setAuthorization(ctx: Context, authorization: String) {
        val pref = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(myAuth, authorization)
        editor.apply()
    }

    fun getAuthorization(ctx: Context) : String {
        val pref = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        return pref.getString(myAuth, "")
    }
    fun setId(ctx: Context, id: String) {
        val pref = ctx.getSharedPreferences("ID", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("ID", id)
        editor.apply()
    }

    fun getId(ctx: Context) : String {
        val pref = ctx.getSharedPreferences("ID", Context.MODE_PRIVATE)
        return pref.getString("ID", "")
    }

    fun setPw(ctx : Context, pw: String){
        val pref = ctx.getSharedPreferences("PW", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("PW", pw)
        editor.apply()
    }

    fun getPw(ctx : Context) : String{
        val pref = ctx.getSharedPreferences("PW", Context.MODE_PRIVATE)
        return pref.getString("PW", "")
    }

    fun setFlag(ctx : Context, flag: Boolean){
        val pref = ctx.getSharedPreferences("FLAG", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("FLAG", flag)
        editor.apply()
    }

    fun getFlag(ctx : Context) : Boolean{
        val pref = ctx.getSharedPreferences("FLAG", Context.MODE_PRIVATE)
        return pref.getBoolean("FLAG", false)
    }

    fun clearSPC (ctx : Context) {
        val pref = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear()
        editor.apply()
        // 앱 시작에 대한 Flag는 유지
        setFlag(ctx, getFlag(ctx))
    }
}