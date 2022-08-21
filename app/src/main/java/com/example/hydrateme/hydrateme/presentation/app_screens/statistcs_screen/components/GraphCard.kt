package com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hydrateme.hydrateme.domain.model.History

@Composable
fun GraphCard(
    history: List<History>,
) {

    val maximumValue = remember {
        if(history.isNotEmpty())
            history.maxOf { it.drinkAmount }
        else
            500
    }

    val amountList = remember {
        val decreaseSize = maximumValue / 5
        (maximumValue + decreaseSize..0 step decreaseSize).map {
            it
        }
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
        .height(348.dp)
        .shadow(5.dp, RoundedCornerShape(10.dp),true),
        shape = RoundedCornerShape(10.dp),
    ) {

    }
}

@Preview
@Composable
fun PreviewGraphCard() {
    GraphCard(history = emptyList())
}