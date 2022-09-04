package com.example.hydrateme.hydrateme.data.local.dto

import android.util.Log
import androidx.room.Entity

@Entity(primaryKeys = ["dayTimestamp","alarmId"])
data class AlarmDayEntity(
    val dayTimestamp: Long,
    val alarmId: Int
)
