package com.example.hydrateme.hydrateme.presentation.app_start_screens.util.componants


import android.text.TextPaint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hydrateme.ui.theme.Gray300
import com.example.hydrateme.ui.theme.LightGray
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import java.lang.Exception


@OptIn(ExperimentalSnapperApi::class)
@Composable
fun ScrollPicker(
    modifier: Modifier = Modifier,
    leftList: MutableList<String>,
    rightList: MutableList<String>,
    highLightedColor: Color = MaterialTheme.colors.primary,
    unHighlightedColor: Color = LightGray,
    lineColor: Color = Gray300,
    lineStrokeWidth: Float = 2f,
    onLeftValueChange: (String) -> Unit,
    onRightValueChange: (String) -> Unit,
    leftInitialItemIndex: Int = 5,
    rightInitialIndex: Int = 0,
    time: Boolean,
) {

    LaunchedEffect(key1 = true) {
        leftList.add("")
        leftList.add(0, "")
        rightList.add("")
        rightList.add(0, "")
    }

    Row(
        modifier = Modifier
            .drawWithContent {
                drawContent()
                val width = this.size.width
                val height = this.size.height
                drawLine(
                    color = lineColor,
                    start = Offset(0f, height * 0.33f),
                    end = Offset(width, height * 0.33f),
                    strokeWidth = lineStrokeWidth
                )
                drawLine(
                    color = lineColor,
                    start = Offset(0f, height * 0.66f),
                    end = Offset(width, height * 0.66f),
                    strokeWidth = lineStrokeWidth
                )

                if (time) {
                    drawCircle(
                        color = highLightedColor,
                        radius = 7f,
                        center = Offset(center.x, center.y - 20f)
                    )

                    drawCircle(
                        color = highLightedColor,
                        radius = 7f,
                        center = Offset(center.x, center.y + 20f)
                    )
                }
            }
            .height(160.dp)
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        val leftScrollState = rememberLazyListState()
        var middleItemLeftList by remember {
            mutableStateOf(leftInitialItemIndex)
        }
        //Left list
        LazyColumn(
            modifier = Modifier
                .background(Color.White)
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = leftScrollState,
            flingBehavior = rememberSnapperFlingBehavior(leftScrollState),

        ) {
            itemsIndexed(leftList) { index, item ->
                Text(
                    text = item,
                    style = MaterialTheme.typography.h3,
                    color = if (index == middleItemLeftList) highLightedColor else unHighlightedColor,
                    fontSize = 28.sp
                )
            }
        }
        //The initial scroll animation
        LaunchedEffect(key1 = true) {
            leftScrollState.animateScrollToItem(
                leftInitialItemIndex,
                0
            )
        }
        //Get the middle item
        middleItemLeftList = leftScrollState.firstVisibleItemIndex + 1
        LaunchedEffect(key1 = middleItemLeftList) {
            onLeftValueChange(leftList[middleItemLeftList])
        }

        val rightScrollState = rememberLazyListState()
        var middleItemRightList by remember {
            mutableStateOf(rightInitialIndex)
        }
        //Right list
        LazyColumn(
            modifier = Modifier
                .background(Color.White)
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(if (time) 25.dp else 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = rightScrollState,
            flingBehavior = rememberSnapperFlingBehavior(rightScrollState)

        ) {
            itemsIndexed(rightList) { index, item ->
                Text(
                    text = item,
                    style = MaterialTheme.typography.h3,
                    color = if (index == middleItemRightList) highLightedColor else unHighlightedColor,
                    fontSize = if (time) 28.sp else 20.sp
                )
            }
        }
        //The initial scroll animation
        LaunchedEffect(key1 = true) {
            rightScrollState.animateScrollToItem(
                rightInitialIndex,
                0
            )
        }
        //Get the middle item
        middleItemRightList = rightScrollState.firstVisibleItemIndex + 1
        LaunchedEffect(key1 = middleItemRightList){
            onRightValueChange(rightList[middleItemRightList])
        }
    }
}


