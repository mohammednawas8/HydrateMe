package com.example.hydrateme.hyprateme.data.local.dto

import androidx.room.Embedded
import androidx.room.Relation

data class UserAndHistory(
    @Embedded val userEntity: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val historyEntity: HistoryEntity
)
