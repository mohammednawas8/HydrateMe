package com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hydrateme.ui.theme.Blue400
import com.example.hydrateme.ui.theme.Blue750

@Composable
fun PeriodReportRow(
    modifier: Modifier = Modifier,
    items: List<String> = listOf("D", "W", "M", "Y"),
    onClick: (String) -> Unit,
) {
    var selectedItem by remember {
        mutableStateOf(0)
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(Blue750)
                .size(width = 240.dp, height = 50.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.forEachIndexed { i, item ->
                PeriodItem(onClick = {
                    selectedItem = i
                    onClick(item)
                },
                    isSelected = selectedItem == i,
                    item = item)
            }
        }
    }
}

@Composable
fun PeriodItem(
    onClick: () -> Unit,
    isSelected: Boolean,
    item: String,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(if (isSelected) Color.White else Color.Transparent)
            .clickable { onClick() }
            .size(width = 60.dp, height = 50.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = item,
            style = MaterialTheme.typography.h2,
            color = Blue400,
            textAlign = TextAlign.Center,
            fontSize = 14.sp)
    }
}

@Preview
@Composable
fun PreviewReportRow() {
    MaterialTheme {
        PeriodReportRow(){}
    }
}
