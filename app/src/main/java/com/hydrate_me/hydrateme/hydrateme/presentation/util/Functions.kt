package com.hydrate_me.hydrateme.hydrateme.presentation.util

import kotlin.math.roundToInt

fun calculateDailyGoal(weight: Int, unit: String): Int {
    var weightInKg = weight * 30 + 100
    if (unit == "ib") {
        weightInKg = (weight * 2.20462262).roundToInt() * 30 + 100
    }
    return weightInKg
}