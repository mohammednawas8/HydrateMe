package com.hydrate_me.hydrateme.hydrateme.domain.use_case

import com.hydrate_me.hydrateme.hydrateme.domain.alarm_manger.ReminderManger
import com.hydrate_me.hydrateme.hydrateme.domain.model.User
import com.hydrate_me.hydrateme.hydrateme.domain.repository.HydrateRepository

class ResetUserDataUseCase(
    private val hydrateRepository: HydrateRepository,
    private val reminderManger: ReminderManger
) {

    suspend operator fun invoke(user: User){
        cancelReminders()
        clearAlarmsTable()
        insertNewAlarms(user)
        scheduleNewReminders()
    }

    private suspend fun scheduleNewReminders() {
        reminderManger.setDrinkReminders()
    }

    private suspend fun insertNewAlarms(user: User) {
       SaveReminderAlarmsUseCase(hydrateRepository).invoke(
            "${user.wakeUpHour}:${user.wakeUpMinutes}",
            "${user.bedHour}:${user.bedMinutes}",
            user.cupSize,
            user.dailyGoal
        )
    }

    private suspend fun clearAlarmsTable() {
        ClearAlarmsTableUseCase(hydrateRepository).invoke()
    }

    private suspend fun cancelReminders() {
        reminderManger.cancelAllReminders()
    }
}