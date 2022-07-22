package com.example.hydrateme.hyprateme.data.local.dto

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val gender: String,
    val weight: Int,
    val wakeTime: String,
    val bedTime: String,
    val dailyGoal: Int,
    val complete: Int,
    @Embedded val unit: Unit,
    val language: String,
    val soundPath: String,
    @PrimaryKey val id: Int? = null,
)

data class Unit(
    val weightUnit: String,
    val liquidUnit: String,
)
