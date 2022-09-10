package com.example.hydrateme.hydrateme.presentation.app_screens.settings_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.window.DialogProperties
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_start_screens.gender_screen.componants.GenderPicker
import com.example.hydrateme.hydrateme.presentation.util.Gender
import com.example.hydrateme.hydrateme.presentation.util.components.DialogTextButtons

@Composable
fun ChangeGenderDialog(
    onDismissRequest: () -> Unit,
    onSaveClick: (Gender) -> Unit,
    gender: Gender,
) {

    var selected by remember {
        mutableStateOf(gender)
    }
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White),
        ) {
            Column() {
                Text(
                    text = stringResource(id = R.string.choose_gender),
                    style = MaterialTheme.typography.h2,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    GenderPicker(gender = Gender.Male,
                        onClick = { selected = Gender.Male },
                        isHappy = selected is Gender.Male,
                        dripSize = 80.dp
                    )
                    GenderPicker(gender = Gender.Female,
                        onClick = { selected = Gender.Female },
                        isHappy = selected is Gender.Female,
                        dripSize = 80.dp)
                }

                DialogTextButtons(
                    onCancelClick = { onDismissRequest() },
                    onSaveClick = { onSaveClick(selected) })
            }
        }
    }
}