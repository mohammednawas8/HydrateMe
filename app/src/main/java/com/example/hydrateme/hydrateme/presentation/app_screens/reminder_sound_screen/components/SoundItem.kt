package com.example.hydrateme.hydrateme.presentation.app_screens.reminder_sound_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_screens.reminder_sound_screen.NotificationSound

@Composable
fun SoundItem(
    soundItem: NotificationSound,
    onItemClick: (NotificationSound) -> Unit,
    isSelected: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(70.dp)
            .clickable {
                onItemClick(soundItem)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${soundItem.name}",
            fontSize = 18.sp
        )

        if (isSelected)
            Icon(painter = painterResource(id = R.drawable.ic_check),
                tint = MaterialTheme.colors.primary,
                contentDescription = "Checked")

    }
}