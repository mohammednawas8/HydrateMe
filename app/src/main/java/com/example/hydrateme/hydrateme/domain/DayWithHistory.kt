package com.example.hydrateme.hydrateme.domain

import com.example.hydrateme.hydrateme.domain.model.Day
import com.example.hydrateme.hydrateme.domain.model.History

data class DayWithHistory(
    val day: Day,
    val history: List<History>
)
