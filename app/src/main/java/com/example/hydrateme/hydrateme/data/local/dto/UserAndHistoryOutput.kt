package com.example.hydrateme.hydrateme.data.local.dto

import androidx.room.Embedded
import androidx.room.Relation

data class UserAndHistoryOutput(
    @Embedded val userEntity: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val historyEntity: HistoryEntity
)
