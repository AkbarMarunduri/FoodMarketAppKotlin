package com.akbarprojec.foodmarket_kotlin.utils

    import android.widget.TextView
import com.google.gson.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object Helpers {
    fun TextView.formatPrice(value: String) {
        this.text = getCurencyIDR(java.lang.Double.parseDouble(value.toString()))
    }

    fun getCurencyIDR(price: Double): String {
        val format = DecimalFormat("#,###,###")
        return "IDR" + format.format(price).replace(",".toRegex(), ".")
    }

    fun getDefaultGson(): Gson? {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
            .registerTypeAdapter(Date::class.java, JsonDeserializer { json, _, _ ->
                val formatServe = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)
                formatServe.timeZone = TimeZone.getTimeZone("UTC")
                formatServe.parse(json.asString)
            })
            .registerTypeAdapter(Date::class.java, JsonSerializer<Date> { src, _, _ ->
                val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)
                format.timeZone = TimeZone.getTimeZone("UTC")
                if (src != null) {
                    JsonPrimitive(format.format(src))
                } else {
                    null
                }
            }).create()
    }

    fun Long.converLongToTime(formatTanggall:String) :String{
        val date=Date(this)
        val format=SimpleDateFormat(formatTanggall)
        return format.format(date)
    }
}