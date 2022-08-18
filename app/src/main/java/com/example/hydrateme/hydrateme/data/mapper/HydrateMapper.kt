package com.example.hydrateme.hydrateme.data.mapper

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
    return User(
        gender, weight,
        wakeUpHour ,wakeUpMinutes,
        bedHour, bedMinutes ,
        dailyGoal, complete, unit, soundPath
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        gender, weight,
        wakeUpHour ,wakeUpMinutes,
        bedHour, bedMinutes ,
        dailyGoal, complete, unit, soundPath
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

fun DayEntityWithHistoryEntity.toReport(): Report{
    return Report(
        day = day.toDay(),
        history = historyEntity.map { it.toHistory() }

    )
}



