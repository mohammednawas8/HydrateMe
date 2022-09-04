package com.example.hydrateme.hydrateme.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlarmEntity(
    val hour: Int,
    val isEnabled: Boolean,
    val minute: Int,
    val userId: Int,
    @PrimaryKey
    val alarmId: Int,
)