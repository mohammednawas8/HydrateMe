package com.example.hydrateme.hydrateme.presentation.app_screens.settings_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.componants.ScrollPicker
import com.example.hydrateme.hydrateme.presentation.util.components.DialogTextButtons
import java.util.*

@Composable
fun ChangeTimeDialog(
    text: String,
    initialHour: Int,
    initialMinute: Int,
    onDismissRequest:()-> Unit,
    onCancelClick:() -> Unit,
    onSaveClick:(hour: Int, minute: Int) -> Unit,
) {

    var selectedHour by remember {
        mutableStateOf(initialHour)
    }

    var selectedMinute by remember {
        mutableStateOf(initialMinute)
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White),
        ) {
            Column {
                Text(
                    text = text,
                    style = MaterialTheme.typography.h2,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp))

                val leftList = remember {
                    (1..24).map { String.format(Locale.ENGLISH,"%02d", it) }.toMutableList()
                }
                val rightList = remember {
                    (0..59).map { String.format(Locale.ENGLISH,"%02d", it) }.toMutableList()
                }

                ScrollPicker(
                    leftList = leftList,
                    rightList = rightList,
                    onLeftValueChange = { selectedHour = it.toInt() },
                    onRightValueChange = { selectedMinute = it.toInt() },
                    time = true,
                    leftInitialItemIndex = initialHour - 1,
                    rightInitialIndex = initialMinute)

                DialogTextButtons(
                    onCancelClick = onCancelClick,
                    onSaveClick = {
                        onSaveClick(selectedHour,selectedMinute)
                    }
                )
            }
        }
    }

}