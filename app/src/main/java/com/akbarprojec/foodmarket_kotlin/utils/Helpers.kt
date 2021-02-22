package com.akbarprojec.foodmarket_kotlin.utils

import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonSerializer
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object Helpers {
    fun TextView.formatPrice(value: String) {
        this.text=getCurencyIDR(java.lang.Double.parseDouble(value))
    }

    fun getCurencyIDR(price: Double) :String{
        val format=DecimalFormat("#,###,###")
        return "IDR"+format.format(price).replace(",".toRegex(),".")
    }

    fun getDefaultGson(): GsonBuilder? {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat("")
            .registerTypeAdapter(Date::class.java, JsonDeserializer{json,_,_,->
                val formatServer = SimpleDateFormat("", Locale.ENGLISH)
                formatServer.timeZone = TimeZone.getTimeZone("UTC")
                formatServer.parse(json.asString)
            })
            .registerTypeAdapter(Date::class.java, JsonSerializer<Date>) {src._,_,->
                val format=SimpleDateFormat("",Locale.ENGLISH)
                format.timeZone= TimeZone.getTimeZone("UTC")
                if (src != null) {
                    format.parse(json.asString)
                } else {
                    null
                }
            }).create()
    }
}