package com.loc.hydrateme.hydrateme.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey
    val time: Long,
    val drinkAmount: Int,
    val day: Long
)
