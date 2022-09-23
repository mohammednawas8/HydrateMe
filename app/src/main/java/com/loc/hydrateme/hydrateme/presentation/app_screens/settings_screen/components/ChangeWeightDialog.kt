package com.loc.hydrateme.hydrateme.presentation.app_screens.settings_screen.components

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
import com.loc.hydrateme.hydrateme.presentation.app_start_screens.util.componants.ScrollPicker
import com.loc.hydrateme.hydrateme.presentation.util.components.DialogTextButtons
import com.loc.hydrateme.R

@Composable
fun ChangeWeightDialog(
    weight: Int,
    weightUnit: String,
    onDismissRequest: () -> Unit,
    onCancelClick: () -> Unit,
    onSaveClick: (weight: Int, unit: String) -> Unit,
) {
    var selectedWeight by remember {
        mutableStateOf(weight)
    }

    var selectedWeightUnit by remember {
        mutableStateOf(weightUnit)
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White),
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.weight_text),
                    style = MaterialTheme.typography.h2,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp))

                val leftList = remember {
                    (40..200).map { it.toString() }.toMutableList()
                }
                val kg = stringResource(id = R.string.kg)
                val ib = stringResource(id = R.string.ib)
                val rightList = remember {
                    mutableListOf(kg, ib)
                }

                ScrollPicker(
                    leftList = leftList,
                    rightList = rightList,
                    onLeftValueChange = { selectedWeight = it.toInt() },
                    onRightValueChange = { selectedWeightUnit = it },
                    time = false,
                    leftInitialItemIndex = leftList.indexOf(weight.toString()),
                    rightInitialIndex = if (weightUnit == "kg") 0 else 1, )

                DialogTextButtons(
                    onCancelClick = onCancelClick,
                    onSaveClick = {
                        onSaveClick(selectedWeight, selectedWeightUnit)
                    })
            }
        }
    }
}