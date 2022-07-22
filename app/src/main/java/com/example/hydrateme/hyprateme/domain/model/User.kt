package com.example.hydrateme.hyprateme.domain.model

import com.example.hydrateme.hyprateme.data.local.dto.Unit

data class User(
    val gender: String,
    val weight: Int,
    val wakeTime: String,
    val bedTime: String,
    val dailyGoal: Int,
    val complete: Int,
    val unit: Unit,
    val language: String,
    val soundPath: String,
)
