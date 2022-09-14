package com.hydrate_me.hydrateme.hydrateme.data.local.dto

import androidx.room.Embedded
import androidx.room.Relation

data class DayEntityWithHistoryEntity(
    @Embedded  val day: DayEntity,
    @Relation(
        entityColumn = "day",
        parentColumn = "day"
    )
    val historyEntity: List<HistoryEntity>,
)