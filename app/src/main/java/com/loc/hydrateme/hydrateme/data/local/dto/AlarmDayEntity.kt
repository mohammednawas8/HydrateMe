package com.loc.hydrateme.hydrateme.data.local.dto

import androidx.room.Entity

@Entity(primaryKeys = ["dayTimestamp","alarmId"])
data class AlarmDayEntity(
    val dayTimestamp: Long,
    val alarmId: Int
)
