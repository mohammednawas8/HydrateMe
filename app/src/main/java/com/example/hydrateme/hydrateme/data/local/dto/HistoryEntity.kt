package com.example.hydrateme.hydrateme.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey val time: Long,
    val drinkedAmount: Int,
    val id: Int
)
