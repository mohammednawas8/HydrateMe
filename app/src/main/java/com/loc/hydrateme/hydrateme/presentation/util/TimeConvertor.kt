package com.loc.hydrateme.hydrateme.presentation.util

import java.text.SimpleDateFormat
import java.util.*

fun yyyyMMddToMillis(date: String): Long {
    val formatYYYY_MM_DD = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    return formatYYYY_MM_DD.parse(date)?.time ?: 0
}

fun hhMM24toAmPm(
    hh: Int, mm: Int,
): String {

    val mmTwoDigits = String.format("%02d", mm)

    return if (hh in 13..23) {
        val hhTwoDigits = String.format("%02d", hh - 12)
        "$hhTwoDigits:$mmTwoDigits PM"

    } else {
        val hhTwoDigits = if (hh == 24)
            String.format("%02d", hh - 12)
        else
            String.format("%02d", hh)

        "$hhTwoDigits:$mmTwoDigits AM"
    }
}