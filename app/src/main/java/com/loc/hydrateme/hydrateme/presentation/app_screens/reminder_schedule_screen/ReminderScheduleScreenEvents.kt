package com.loc.hydrateme.hydrateme.presentation.app_screens.reminder_schedule_screen

import com.loc.hydrateme.hydrateme.domain.model.Alarm

sealed class ReminderScheduleScreenEvents{
    data class SwitchChange(val isChecked: Boolean, val alarm: Alarm): ReminderScheduleScreenEvents()
    data class SetAlarm(val hour: Int, val min: Int): ReminderScheduleScreenEvents()
}
