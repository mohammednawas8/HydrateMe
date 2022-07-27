package com.example.hydrateme.hydrateme.domain.model

import com.example.hydrateme.hydrateme.data.local.dto.Unit

data class User(
    val gender: String,
    val weight: Int,
    val wakeTime: String,
    val bedTime: String,
    val dailyGoal: Int,
    val complete: Int,
    val unit: Unit,
    val soundPath: String,
)
