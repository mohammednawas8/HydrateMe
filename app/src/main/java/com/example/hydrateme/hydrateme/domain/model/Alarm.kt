package com.example.hydrateme.hydrateme.domain.model

data class Alarm(
    val hour:Int,
    val minute: Int,
    val daysTimeStamp: List<Long>,
    val isEnabled: Boolean
)
