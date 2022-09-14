package com.hydrate_me.hydrateme.hydrateme.domain.model

import com.hydrate_me.hydrateme.hydrateme.data.local.dto.Unit

data class User(
    val gender: String,
    val weight: Int,
    val wakeUpHour: String,
    val wakeUpMinutes: String,
    val cupSize: Int,
    val bedHour: String,
    val bedMinutes: String,
    val dailyGoal: Int,
    val complete: Int,
    val unit: Unit,
    val soundPath: Int?,
)
