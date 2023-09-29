package com.elm.elmassignment.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object Utils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun parseIncidentDate(date: String?): String {
        val inputPattern = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'"
        var inputDate: LocalDate? = null
        var outputDate: String? = null
        val inputFormatter = DateTimeFormatter.ofPattern(inputPattern, Locale.ENGLISH)
        try {
            inputDate = LocalDate.parse(date, inputFormatter)
            outputDate = inputDate.toString()
        } catch (e: Exception) {
            return "no date"
        }
        return outputDate

    }
}