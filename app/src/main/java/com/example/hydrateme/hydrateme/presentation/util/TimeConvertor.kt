package com.example.hydrateme.hydrateme.presentation.util

import java.text.SimpleDateFormat
import java.util.*

fun yyyyMMddToMillis(date: String): Long{
    val formatYYYY_MM_DD = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    return formatYYYY_MM_DD.parse(date)?.time ?: 0
}