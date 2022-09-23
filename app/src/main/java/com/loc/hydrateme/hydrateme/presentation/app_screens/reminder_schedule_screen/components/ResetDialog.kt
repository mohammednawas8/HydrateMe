package com.loc.hydrateme.hydrateme.presentation.app_screens.reminder_schedule_screen.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.loc.hydrateme.R

@Composable
fun ResetDialog(
    onConfirmClick: () -> Unit,
    onCancelClick:() -> Unit,
    onDismissRequest: () -> Unit
) {

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
                Text(text = stringResource(id = R.string.reset_data),color = MaterialTheme.colors.primary)
        },
        text = {
               Text(text = stringResource(id = R.string.reset_data_text))
        },
        confirmButton = {
            TextButton(onClick = onConfirmClick) {
                Text(text = stringResource(id = R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelClick) {
                Text(text = stringResource(id = R.string.cancel), color = MaterialTheme.colors.primary)
            }
        },
        modifier = Modifier.width(300.dp).height(150.dp),
        shape = RoundedCornerShape(10.dp)
    )

}