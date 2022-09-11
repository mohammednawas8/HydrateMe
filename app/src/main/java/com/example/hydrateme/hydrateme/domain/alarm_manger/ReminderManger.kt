package com.example.hydrateme.hydrateme.domain.alarm_manger

import com.example.hydrateme.hydrateme.domain.model.Alarm

interface ReminderManger {

    fun setDrinkReminders()

    fun cancelDrinkReminder(alarm: Alarm)

    fun updateDrinkReminder(alarm: Alarm)

    fun setNextDayReminders(time: Long, alarmId: Int)

    fun setInsertDayAlarm()

    suspend fun cancelAllReminders()


}