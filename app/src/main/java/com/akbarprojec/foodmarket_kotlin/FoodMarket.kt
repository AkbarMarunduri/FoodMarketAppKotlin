package com.akbarprojec.foodmarket_kotlin

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.multidex.MultiDexApplication
import com.akbarprojec.foodmarket_kotlin.networks.HttpClient

class   FoodMarket:MultiDexApplication() {
    companion object{
        lateinit var instance:FoodMarket
        fun getApp(): FoodMarket {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance =this
    }
    fun getPreference(): SharedPreferences { 
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun setToken(token:String) {
        getPreference().edit().putString("PREFERENCE_TOKEN", token).apply()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken(): String? {
        return getPreference().getString("PREFERENCE_TOKEN",null)
    }

    fun setUSer(user: String) {
        getPreference().edit().putString("PREFERENCE_USER", user).apply()
//        HttpClient.getInstance().buildRetrofitClient(user)
    }

    fun getUser(): String? {
        return getPreference().getString("PREFERENCE_USER", null)
    }
}