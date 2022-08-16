package com.example.hydrateme.hydrateme.data.mapper

import android.util.Log
import com.example.hydrateme.hydrateme.data.local.dto.DayEntity
import com.example.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hydrateme.data.local.dto.UserAndDaysOutput
import com.example.hydrateme.hydrateme.data.local.dto.UserEntity
import com.example.hydrateme.hydrateme.domain.model.Day
import com.example.hydrateme.hydrateme.domain.model.History
import com.example.hydrateme.hydrateme.domain.model.User
import com.example.hydrateme.hydrateme.domain.model.UserAndDays

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

fun HistoryEntity.toHistory(): History{
    return History(
        time,
        drinkAmount
    )
}



