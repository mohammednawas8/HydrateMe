package com.loc.hydrateme.hydrateme.presentation.app_screens.privacy_policy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PointItem(
    text: String
) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier
            .size(5.dp)
            .background(Color.Black))
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color.DarkGray
        )
    }

}