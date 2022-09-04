package com.example.hydrateme.hydrateme.domain.use_case

import android.app.AlarmManager
import android.app.PendingIntent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi


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