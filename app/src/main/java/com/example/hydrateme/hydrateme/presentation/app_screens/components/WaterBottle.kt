package com.example.hydrateme.hydrateme.presentation.app_screens.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hydrateme.hydrateme.presentation.util.fromToQuadBezier
import com.example.hydrateme.ui.theme.Blue700

@Composable
fun WaterBottle(
    modifier: Modifier = Modifier,
    @FloatRange waterPercentage: Float,
) {

    Box(modifier = modifier, contentAlignment = Alignment.BottomCenter) {
        val afterMathPercentage = remember {
            1 - waterPercentage
        }
        Canvas(modifier = Modifier.matchParentSize()) {
            val width = size.width
            val height = size.height
            //Bottle body
            drawRoundRect(
                size = Size(width, height * 0.6f),
                topLeft = Offset(0f, height * 0.4f),
                color = Color.White,
                cornerRadius = CornerRadius(20f, 20f)
            )

            //Bottle neck
            drawRect(
                size = Size(width * 0.45f, height * 0.1f),
                topLeft = Offset(width * 0.55f / 2, height * 0.13f),
                color = Color.White
            )

            //Bottle curves
            drawCircle(
                center = Offset(width / 2f, height * 0.4f),
                radius = width * 0.5f,
                color = Color.White
            )

            //Bottle cap
            drawRoundRect(
                color = Color(0xFF70BDF2),
                size = Size(width * 0.55f, height * 0.14f),
                topLeft = Offset(width * 0.23f, height * 0.03f),
                cornerRadius = CornerRadius(45f, 45f)
            )


            //Dark Water waves
            val point0 = Offset(0f,height)
            val point1 = Offset(0f, height * afterMathPercentage + 10f)
            val point2 = Offset(width * 0.1f, height * afterMathPercentage)
            val point3 = Offset(width * 0.2f, height * afterMathPercentage + 50f)
            val point4 = Offset(width * 0.3f, height * afterMathPercentage + 10f)
            val point5 = Offset(width * 0.5f, height * afterMathPercentage + 75f)
            val point6 = Offset(width * 0.7f, height * afterMathPercentage - 10f)
            val point7 = Offset(width * 0.9f, height * afterMathPercentage + 20)
            val point8 = Offset(width * 1.1f, height * afterMathPercentage - 10f)
            val waterPath = Path().apply {
                moveTo(point0.x, point0.y)
                fromToQuadBezier(point0,point1)
                lineTo(point1.x,point1.y)
                fromToQuadBezier(point1,point2)
                fromToQuadBezier(point2,point3)
                fromToQuadBezier(point3,point4)
                fromToQuadBezier(point4,point5)
                fromToQuadBezier(point5,point6)
                fromToQuadBezier(point6,point7)
                fromToQuadBezier(point7,point8)
                lineTo(width, height)
                close()
            }

            drawPath(
                path = waterPath,
                color = Blue700,

            )

        }
    }
}

@Preview
@Composable
fun PreviewWatterBottle() {
    MaterialTheme {
        WaterBottle(waterPercentage = 0.8f, modifier = Modifier
            .width(136.dp)
            .height(330.dp))
    }

}
