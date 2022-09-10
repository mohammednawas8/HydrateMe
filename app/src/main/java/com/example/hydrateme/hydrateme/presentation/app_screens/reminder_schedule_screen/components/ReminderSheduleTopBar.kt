package com.example.hydrateme.hydrateme.presentation.app_screens.reminder_schedule_screen.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hydrateme.R

@Composable
fun ReminderScheduleTopBar(
    onBackClick:() -> Unit,
    onAddClick: () -> Unit
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
                    Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "Navigate back",Modifier.size(20.dp))
                }
                Text(text = stringResource(id = R.string.reminder_schedule),
                    color = Color.White,
                    style = MaterialTheme.typography.h2,
                    fontSize = 18.sp,
                )
            }
            IconButton(onClick = onAddClick) {
                Text(text = "+",
                    color = Color.White,
                    style = MaterialTheme.typography.h2,
                    fontSize = 33.sp,
                )
            }
        }
    }
}