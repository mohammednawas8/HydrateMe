package com.example.hydrateme.hydrateme.presentation.app_screens.drink_screen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.hydrateme.ui.theme.HydrateMeTheme
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_screens.drink_screen.CupItem


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CupPickerDialog(
    cupList: List<CupItem>,
    onDismissRequest: () -> Unit,
    onSaveClick: (CupItem) -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = true)
    ) {
        Box(modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)) {
            Column {
                Text(
                    text = stringResource(id = R.string.cup_size),
                    style = MaterialTheme.typography.h2,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp)
                )

                var selectedCup by remember {
                    mutableStateOf(-1)
                }
                LazyVerticalGrid(
                    cells = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .padding(16.dp)) {
                    itemsIndexed(cupList) { i, item ->
                        CupItem(cup = item, isSelected = i == selectedCup) {
                            selectedCup = i
                        }
                    }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    TextButton(
                        onClick = onDismissRequest,
                    ) {
                        Text(text = stringResource(id = R.string.cancel))
                    }
                    TextButton(
                        onClick = { onSaveClick(cupList[if (selectedCup == -1) 2 else selectedCup]) },
                    ) {
                        Text(text = stringResource(id = R.string.save),
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CupItem(
    modifier: Modifier = Modifier,
    cup: CupItem,
    isSelected: Boolean,
    onClick: (CupItem) -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.clickable { onClick(cup) }
    ) {
        Icon(painter = painterResource(id = cup.icon),
            contentDescription = cup.amount.toString(),
            modifier = Modifier.size(60.dp),
            tint = if (isSelected) MaterialTheme.colors.primary else Color.DarkGray
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "${cup.amount} ${cup.unit}",
            color = if (isSelected) MaterialTheme.colors.primary else Color.DarkGray)
    }
}

@Preview
@Composable
fun CupPickerDialogPreview() {
    HydrateMeTheme {
        CupPickerDialog(emptyList(), onDismissRequest = {}) {

        }
    }
}