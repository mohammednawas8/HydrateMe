package com.loc.hydrateme.hydrateme.domain.model

data class Alarm(
    val id: Int,
    val hour:Int,
    val minute: Int,
    val daysTimeStamp: List<Long>,
    val isEnabled: Boolean
)
