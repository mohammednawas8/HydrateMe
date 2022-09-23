package com.loc.hydrateme.hydrateme.data.alarm_manger

import java.util.*

fun getWeekDaysCalender(): List<Calendar> {
    val sunday = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
    }
    val monday = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
    }
    val tuesday = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY)
    }
    val wednesday = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY)
    }
    val thursday = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY)
    }
    val friday = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
    }
    val saturday = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
    }

    return listOf(sunday,
        monday,
        tuesday,
        wednesday,
        thursday,
        friday,
        saturday)
}