package com.hydrate_me.hydrateme.hydrateme.domain.alarm_manger

import com.hydrate_me.hydrateme.hydrateme.domain.model.Alarm

interface ReminderManger {

    fun setDrinkReminders()

    fun cancelDrinkReminder(alarm: Alarm)

    fun updateDrinkReminder(alarm: Alarm)

    fun setNextDayReminders(time: Long, alarmId: Int)

    fun setInsertDayAlarm()

    suspend fun cancelAllReminders()


}