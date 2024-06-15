package com.example.navbar.util

import android.content.Context
import android.content.SharedPreferences

class onBoardingUtils( val context: Context) {

    fun isOnBoardingComplete():Boolean{

        return context.getSharedPreferences("onboarding",Context.MODE_PRIVATE)
            .getBoolean("complete",false)
    }

    fun setOnBoardingComplete(){

        return context.getSharedPreferences("onboarding",Context.MODE_PRIVATE)
            .edit()
            .putBoolean("complete",true)
            .apply()
    }
}