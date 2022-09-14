package com.hydrate_me.hydrateme.hydrateme.data.local.dto

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val gender: String,
    val weight: Int,
    val wakeUpHour: String,
    val wakeUpMinutes: String,
    val cupSize: Int = 200,
    val bedHour: String,
    val bedMinutes: String,
    val dailyGoal: Int,
    val complete: Int,
    @Embedded val unit: Unit,
    val soundPath: Int,
    @PrimaryKey val id: Int? = 0,
)

data class Unit(
    val weightUnit: String,
    val liquidUnit: String,
)
