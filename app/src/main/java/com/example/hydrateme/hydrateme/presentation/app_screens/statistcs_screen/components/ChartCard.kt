package com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen.components

import android.util.Log
import com.example.hydrateme.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hydrateme.hydrateme.domain.model.History
import com.example.hydrateme.ui.theme.*

@Composable
fun ChartCard(
    history: List<History>,
    unit: String,
    period: String,
) {

    Log.d("test",history.size.toString())

    val maximumValue by derivedStateOf {
        if (history.isEmpty())
            mutableStateOf(500)
        else {
            mutableStateOf(history.maxOf { it.drinkAmount })
        }
    }.value


    val decreaseSize by derivedStateOf {
        mutableStateOf(maximumValue / 5)
    }.value


    val amountList = derivedStateOf{
        val list = mutableListOf<Int>()
        for (i in maximumValue downTo 0 step decreaseSize) {
            list.add(i)
        }
        list
    }.value

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .height(348.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp), true),
        shape = RoundedCornerShape(10.dp),
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val maxWidth = this.maxWidth
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(horizontal = 11.dp)) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = unit.toUpperCase(),
                        color = Blue650,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(33.dp),
                        modifier = Modifier.drawWithContent {
                            drawContent()
                        }
                    ) {
                        amountList.forEach {
                            Row(verticalAlignment = CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                Text(
                                    text = it.toString(),
                                    color = Gray400,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.End,
                                )
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Gray150)) {
                                }
                            }
                        }
                    }

                    //Day and days in numbers
                    Row(Modifier
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = period,
                            color = Blue650,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold)
                        (1..10).forEach {
                            Text(text = String.format("%02d", it),
                                color = Gray150,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            Box(contentAlignment = BottomCenter,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = maxWidth * 0.14f, bottom = 40.dp, end = 10.dp)) {
                Row(
                    modifier = Modifier
                        .align(BottomCenter)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    history.forEach {
                        Box(modifier = Modifier
                            .height((it.drinkAmount.times(267 / maximumValue.toFloat()).dp))
                            .width(10.dp)
                            .background(Blue750)
                            .align(Bottom))
                    }
                }
            }
            if (history.isEmpty()) {
                Text(text = stringResource(id = R.string.no_report),
                    modifier = Modifier.align(Center),
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.h2,
                    color = Gray300)
            }
        }
    }
}

@Preview
@Composable
fun PreviewGraphCard() {
    ChartCard(history = emptyList(), "ml", "Day")
}