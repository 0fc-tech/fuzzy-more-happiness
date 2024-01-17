package com.example.eni_shop.utils

import java.text.SimpleDateFormat
import java.util.Date

object Converter {

    @JvmStatic
    fun toSimpleString(date: Date?): String {

        if (date != null) {
            val format = SimpleDateFormat("dd/MM/yyy")
            return format.format(date)
        }
        return ""
    }

    @JvmStatic
    fun toTwoDecimalNumber(number : Double) : String{
        return String.format("%.2f", number)
    }



}