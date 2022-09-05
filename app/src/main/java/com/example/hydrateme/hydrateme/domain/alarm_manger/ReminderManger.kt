package com.example.hydrateme.hydrateme.domain.alarm_manger

interface ReminderManger {

    fun setDrinkReminders()

    fun deleteDrinkReminder()

    fun updateDrinkReminder()

    fun setNextDayReminders(time: Long, alarmId: Int)

    fun setInsertDayAlarm()
}