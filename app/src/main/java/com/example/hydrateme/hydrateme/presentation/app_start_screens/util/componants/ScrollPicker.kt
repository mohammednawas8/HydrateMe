package com.example.hydrateme.hydrateme.presentation.app_start_screens.util.componants


import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hydrateme.ui.theme.Gray300
import com.example.hydrateme.ui.theme.LightGray


@Composable
fun ScrollPicker(
    modifier: Modifier = Modifier,
    leftList: List<String>,
    rightList: List<String>,
    highLightedColor: Color = MaterialTheme.colors.primary,
    unHighlightedColor: Color = LightGray,
    lineColor: Color = Gray300,
    lineStrokeWidth: Float = 2f,
    onLeftValueChange: (String) -> Unit,
    onRightValueChange: (String) -> Unit,
    leftInitialItemIndex: Int = 5,
) {

    var leftTop by remember {
        mutableStateOf(0)
    }
    var leftMiddle by remember {
        mutableStateOf(1)
    }
    var leftBottom by remember {
        mutableStateOf(2)
    }



    Row(
        modifier = modifier
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
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        val scrollState = rememberLazyListState()
        val scope = rememberCoroutineScope()

        var value by remember {
            mutableStateOf("")
        }
        //Left list
        LazyColumn(modifier = Modifier
            .background(Color.White)
            .fillMaxHeight()
            .weight(1f),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = scrollState,

        ) {
            itemsIndexed(leftList) { index, item ->
                Text(
                    text = item,
                    style = MaterialTheme.typography.h3,
                    color = unHighlightedColor,
                    fontSize = 28.sp
                )
                LaunchedEffect(key1 = true) {
                    scrollState.animateScrollToItem(
                        leftInitialItemIndex,
                        0)
                }
            }
            val visibleItems = scrollState.layoutInfo.visibleItemsInfo
            if (visibleItems.isNotEmpty()) {
               val middleIndex =  visibleItems[1].index
                value = leftList[middleIndex]
            }
        }

        //Right list
        Column(modifier = Modifier
            .background(Color.White)
            .fillMaxHeight()
            .weight(1f),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = leftList[0],
                style = MaterialTheme.typography.h3,
                color = unHighlightedColor,
                fontSize = 28.sp
            )

            Text(
                text = leftList[1],
                style = MaterialTheme.typography.h3,
                color = highLightedColor,
                fontSize = 28.sp
            )


        }

        Column {
            Text(text =value )
        }
    }
}


