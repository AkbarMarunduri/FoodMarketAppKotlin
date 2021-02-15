package com.akbarprojec.foodmarket_kotlin.utils

import android.widget.TextView
import java.text.DecimalFormat

object Helpers {
    fun TextView.formatPrice(value: String) {
        this.text=getCurencyIDR(java.lang.Double.parseDouble(value))
    }

    fun getCurencyIDR(price: Double) :String{
        val format=DecimalFormat("#,###,###")
        return "IDR"+format.format(price).replace(",".toRegex(),".")
    }
}