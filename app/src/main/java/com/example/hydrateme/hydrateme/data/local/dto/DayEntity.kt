package com.example.hydrateme.hydrateme.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DayEntity(
    @PrimaryKey val day: Long,
    val id: Int
)
