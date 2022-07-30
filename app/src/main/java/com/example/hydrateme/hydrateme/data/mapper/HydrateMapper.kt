package com.example.hydrateme.hydrateme.data.mapper

import com.example.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hydrateme.data.local.dto.UserAndHistoryOutput
import com.example.hydrateme.hydrateme.data.local.dto.UserEntity
import com.example.hydrateme.hydrateme.domain.model.History
import com.example.hydrateme.hydrateme.domain.model.User
import com.example.hydrateme.hydrateme.domain.model.UserAndHistory

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

fun UserAndHistoryOutput.toUserAndHistory(): UserAndHistory {
    return UserAndHistory(
        userEntity.toUser(),
        historyEntity.toHistory()
    )
}



