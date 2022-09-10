package com.example.hydrateme.hydrateme.presentation.app_screens.reminder_schedule_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hydrateme.hydrateme.domain.model.Alarm

@Composable
fun ReminderItem(
    alarm: Alarm,
    onCheckChange: (Boolean,Alarm) -> Unit,
) {
    var isChecked by remember {
        mutableStateOf(alarm.isEnabled)
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(70.dp)) {

        Text(
            text = "${alarm.hour}:${alarm.minute}",
            fontSize = 18.sp
        )

        Switch(checked = isChecked, onCheckedChange = {
            isChecked = !isChecked
            onCheckChange(isChecked,alarm)
        },
            colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colors.primary)
        )
    }
}