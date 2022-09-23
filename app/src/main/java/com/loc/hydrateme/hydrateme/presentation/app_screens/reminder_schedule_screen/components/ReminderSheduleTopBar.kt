package com.loc.hydrateme.hydrateme.presentation.app_screens.reminder_schedule_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loc.hydrateme.R

@Composable
fun ReminderScheduleTopBar(
    onBackClick: () -> Unit,
    onAddClick: () -> Unit,
    text: String,
    showIcon: Boolean,
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackClick) {
                    Icon(painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Navigate back",
                        Modifier.size(20.dp),
                        tint = Color.White)
                }
                Text(
                    text = text,
                    color = Color.White,
                    style = MaterialTheme.typography.h2,
                    fontSize = 18.sp,
                )
            }
            if (showIcon)
                IconButton(onClick = onAddClick) {
                    Text(
                        text = "+",
                        color = Color.White,
                        style = MaterialTheme.typography.h2,
                        fontSize = 33.sp,
                    )
                }
        }
    }
}