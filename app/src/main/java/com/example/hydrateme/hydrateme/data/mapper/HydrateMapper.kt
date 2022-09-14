package com.example.hydrateme.hydrateme.data.mapper

import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.data.local.dto.*
import com.example.hydrateme.hydrateme.domain.model.*

fun DayEntity.toDay(): Day {
    return Day(
        day
    )
}

fun Day.toDayEntity(id: Int): DayEntity {
    return DayEntity(
        day, id
    )
}

fun UserEntity.toUser(): User {
    val wakeUpHour = String.format("%02d", wakeUpHour.toInt())
    val wakeUpMinutes = String.format("%02d", wakeUpMinutes.toInt())
    val bedHour = String.format("%02d", bedHour.toInt())
    val bedMinutes = String.format("%02d", bedMinutes.toInt())
    return User(
        gender, weight,
        wakeUpHour, wakeUpMinutes,
        cupSize, bedHour, bedMinutes,
        dailyGoal, complete, unit, soundPath
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        gender, weight,
        wakeUpHour, wakeUpMinutes,
        cupSize, bedHour, bedMinutes,
        dailyGoal, complete, unit, soundPath ?: R.raw.water_drop_deffault, 0
    )
}

fun UserAndDaysOutput.toUserAndDays(): UserAndDays {
    return UserAndDays(
        userEntity.toUser(),
        dayEntity.toDay()
    )
}

fun HistoryEntity.toHistory(): History {
    return History(
        time,
        drinkAmount
    )
}

fun DayEntityWithHistoryEntity.toReport(): Report {
    return Report(
        day = day.toDay(),
        history = historyEntity.map { it.toHistory() }

    )
}

fun AlarmEntity.toAlarm(): Alarm {
    return Alarm(
        alarmId, hour, minute, emptyList(), isEnabled
    )
}

fun AlarmDay.toAlarmDayEntity(alarmId: Int): AlarmDayEntity {
    return AlarmDayEntity(
        dayTimestamp,
        0 //Auto generate by room
    )
}

fun Alarm.toAlarmEntity(userId: Int, alarmId: Int): AlarmEntity {
    return AlarmEntity(
        hour,
        isEnabled,
        minute,
        userId,
        alarmId
    )
}



