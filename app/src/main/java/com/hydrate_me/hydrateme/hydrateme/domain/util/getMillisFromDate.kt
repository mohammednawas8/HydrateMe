package com.hydrate_me.hydrateme.hydrateme.domain.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun getMilliFromDate(
    dateFormat: String = SimpleDateFormat("MM/dd/yyyy", Locale.US).format(Date())
): Long {
    var date = Date()
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    try {
        date = formatter.parse(dateFormat)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    println("Today is $date")
    return date.time
}