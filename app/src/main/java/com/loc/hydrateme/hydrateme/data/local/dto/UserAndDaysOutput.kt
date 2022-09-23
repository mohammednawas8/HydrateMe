package com.loc.hydrateme.hydrateme.data.local.dto

import androidx.room.Embedded
import androidx.room.Relation

data class UserAndDaysOutput(
    @Embedded val userEntity: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val dayEntity: DayEntity
)
