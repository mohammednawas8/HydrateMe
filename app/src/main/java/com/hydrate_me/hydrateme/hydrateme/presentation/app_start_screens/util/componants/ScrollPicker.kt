package com.hydrate_me.hydrateme.hydrateme.presentation.app_start_screens.util.componants


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hydrate_me.hydrateme.ui.theme.Gray300
import com.hydrate_me.hydrateme.ui.theme.LightGray
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.launch


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

    var leftState by remember {
        mutableStateOf(2)
    }

    var rightState by remember {
        mutableStateOf(2)
    }

    var left = remember {
        mutableStateListOf("")
    }

    var right = remember {
        mutableStateListOf("")
    }



    LaunchedEffect(key1 = leftState) {
        launch {
            left.clear()
            left.add("")
            repeat(leftState) {
                left.addAll(leftList)
            }
            left.add("")
        }
    }

        LaunchedEffect(key1 = rightState) {
            if (!time){
                right.addAll(rightList)
                right.add("")
            }
            else {
                launch {
                    right.clear()
                    right.add("")
                    repeat(rightState) {
                        right.addAll(rightList)
                    }
                    right.add("")
                }
            }
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
            itemsIndexed(left) { index, item ->
                Text(
                    text = item,
                    style = MaterialTheme.typography.h3,
                    color = if (index == middleItemLeftList) highLightedColor else unHighlightedColor,
                    fontSize = 28.sp
                )
            }
        }
        val leftFirstVisibleIndex = remember { mutableStateOf(leftScrollState.firstVisibleItemIndex) }
        if (leftScrollState.shouldLoadMore(leftFirstVisibleIndex)) {
            leftState += 1
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
            if (left.size == 1)
                onLeftValueChange(if (left[0] == "") "0" else left[0])
            else
                onLeftValueChange(left[middleItemLeftList])
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
            itemsIndexed(right) { index, item ->
                Text(
                    text = item.toString(),
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

        if (time){
            val rightFirstVisibleIndex = remember { mutableStateOf(rightScrollState.firstVisibleItemIndex) }
            if (rightScrollState.shouldLoadMore(rightFirstVisibleIndex)) {
                rightState += 1
            }
        }

        //Get the middle item
        middleItemRightList = rightScrollState.firstVisibleItemIndex + 1
        LaunchedEffect(key1 = middleItemRightList) {

            if (right.size == 1)
                onRightValueChange(if (right[0] == "") "0" else right[0])
            else
                onRightValueChange(right[middleItemRightList])
        }
    }
}


fun LazyListState.shouldLoadMore(rememberedIndex: MutableState<Int>): Boolean {
    val firstVisibleIndex = this.firstVisibleItemIndex
    if (rememberedIndex.value != firstVisibleIndex) {
        rememberedIndex.value = firstVisibleIndex
        return layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
    }
    return false
}

