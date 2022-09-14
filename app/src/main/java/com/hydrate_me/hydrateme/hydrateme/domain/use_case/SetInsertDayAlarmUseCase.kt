package com.hydrate_me.hydrateme.hydrateme.domain.use_case

import android.app.AlarmManager
import android.app.PendingIntent


class SetInsertDayAlarmUseCase {

    operator fun invoke(
        alarmManager: AlarmManager,
        pendingIntent: PendingIntent,
        time: Long
    ){
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            time,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }
}