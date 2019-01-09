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
        val pref = ctx.getSharedPreferences("ID", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("PW", pw)
        editor.apply()
    }

    fun getPw(ctx : Context) : String{
        val pref = ctx.getSharedPreferences("ID", Context.MODE_PRIVATE)
        return pref.getString("PW", "")
    }

    fun clearSPC (ctx : Context) {
        val pref = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear()
        editor.apply()
    }
}