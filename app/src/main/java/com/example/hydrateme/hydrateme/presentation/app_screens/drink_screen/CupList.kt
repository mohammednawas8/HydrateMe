package com.example.hydrateme.hydrateme.presentation.app_screens.drink_screen

import androidx.compose.ui.res.painterResource
import com.example.hydrateme.R

data class CupItem(
    val icon: Int,
    val amount: Int,
    val unit: String
)

val cupList = listOf(
    CupItem(R.drawable.cup_100ml,100,"ml"),
    CupItem(R.drawable.cup_150ml,150,"ml"),
    CupItem(R.drawable.cup_200ml,200,"ml"),
    CupItem(R.drawable.cup_300ml,300,"ml"),
    CupItem(R.drawable.cup_400ml,400,"ml"),
    CupItem(R.drawable.cup_500ml,500,"ml"),
)