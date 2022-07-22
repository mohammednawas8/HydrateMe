package com.example.hydrateme.hyprateme.data.mapper

import com.example.hydrateme.hyprateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hyprateme.data.local.dto.UserEntity
import com.example.hydrateme.hyprateme.domain.model.History
import com.example.hydrateme.hyprateme.domain.model.User

fun HistoryEntity.toHistory(): History {
    return History(
        time,
        drinkedAmount
    )
}

fun History.toHistoryEntity(id: Int): HistoryEntity {
    return HistoryEntity(
        time,
        drinkedAmount,
        id
    )
}

fun UserEntity.toUser(): User {
    return User(
        gender, weight, wakeTime, bedTime, dailyGoal, complete, unit, language, soundPath
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        gender, weight, wakeTime, bedTime, dailyGoal, complete, unit, language, soundPath
    )
}