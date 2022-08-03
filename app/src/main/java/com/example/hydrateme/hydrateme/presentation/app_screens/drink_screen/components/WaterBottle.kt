package com.example.hydrateme.hydrateme.presentation.app_screens.drink_screen.components

import android.util.Log
import androidx.annotation.FloatRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hydrateme.hydrateme.presentation.util.fromToQuadBezier
import com.example.hydrateme.ui.theme.Blue500
import com.example.hydrateme.ui.theme.Blue700

@Composable
fun WaterBottle(
    modifier: Modifier = Modifier,
    emptyBottleColor: Color = Color.White,
    darkWavesColor: Color = Blue700,
    lightWavesColor: Color = Blue500,
    lightColor: Boolean = false,
    text: String,
    @FloatRange waterPercentage: Float,
) {
    Box(modifier = modifier, contentAlignment = Alignment.BottomCenter) {
        val afterMathPercentage = remember {
            1 - waterPercentage
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            val bottleBodtPath = Path().apply {
                //Bottle body
                moveTo(width * 0.3f, height * 0.1f)
                lineTo(width * 0.3f, height * 0.2f)
                quadraticBezierTo(
                    0f, height * 0.3f,
                    0f, height * 0.4f
                )
                lineTo(0f, height * 0.95f)
                quadraticBezierTo(
                    0f, height,
                    width * 0.05f, height
                )

                lineTo(width * 0.95f, height)
                quadraticBezierTo(
                    width, height,
                    width, height * 0.95f
                )
                lineTo(width, height * 0.4f)
                quadraticBezierTo(
                    width, height * 0.3f,
                    width * 0.7f, height * 0.2f
                )
                lineTo(width * 0.7f, height * 0.2f)
                lineTo(width * 0.7f, height * 0.1f)
                Log.d("TT","$waterPercentage")

                close()
            }

            drawPath(
                path = bottleBodtPath,
                color = emptyBottleColor
            )

//            Light Water Waves
            if (lightColor)
                clipPath(path = bottleBodtPath) {
                    val point0 = Offset(0f + 35f, height)
                    val point1 = Offset(0f + 35f, height * afterMathPercentage + 10f)
                    val point2 = Offset(width * 0.1f + 35f, height * afterMathPercentage)
                    val point3 = Offset(width * 0.2f + 35f, height * afterMathPercentage + 50f)
                    val point4 = Offset(width * 0.3f + 35f, height * afterMathPercentage + 10f)
                    val point5 = Offset(width * 0.5f + 35f, height * afterMathPercentage + 75f)
                    val point6 = Offset(width * 0.7f + 35f, height * afterMathPercentage - 10f)
                    val point7 = Offset(width * 0.9f + 35f, height * afterMathPercentage + 20)
                    val point8 = Offset(width * 1.1f + 35f, height * afterMathPercentage - 10f)
                    val waterPath = Path().apply {
                        moveTo(point0.x, point0.y)
                        fromToQuadBezier(point0, point1)
                        lineTo(point1.x, point1.y)
                        fromToQuadBezier(point1, point2)
                        fromToQuadBezier(point2, point3)
                        fromToQuadBezier(point3, point4)
                        fromToQuadBezier(point4, point5)
                        fromToQuadBezier(point5, point6)
                        fromToQuadBezier(point6, point7)
                        fromToQuadBezier(point7, point8)
                        close()
                    }
                    drawPath(
                        path = waterPath,
                        color = lightWavesColor,
                    )
                }

            //Dark Water waves
            clipPath(path = bottleBodtPath) {
                val point0 = Offset(0f, height)
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
                    fromToQuadBezier(point0, point1)
                    lineTo(point1.x, point1.y)
                    fromToQuadBezier(point1, point2)
                    fromToQuadBezier(point2, point3)
                    fromToQuadBezier(point3, point4)
                    fromToQuadBezier(point4, point5)
                    fromToQuadBezier(point5, point6)
                    fromToQuadBezier(point6, point7)
                    fromToQuadBezier(point7, point8)
                    lineTo(width, height)
                    close()
                }
                drawPath(
                    path = waterPath,
                    color = darkWavesColor,
                )
            }

            //Bottle cap
            drawRoundRect(
                color = Color(0xFF70BDF2),
                size = Size(size.width * 0.55f, size.height * 0.13f),
                topLeft = Offset(size.width * 0.23f, size.height * 0.03f),
                cornerRadius = CornerRadius(45f, 45f)
            )

        }

        Row(
            modifier = Modifier.fillMaxSize().padding(top = 60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.h3,
                color = if (waterPercentage > 0.5f) Color.White else darkWavesColor,
                fontSize = 40.sp,
            )

            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "ml",
                style = MaterialTheme.typography.h3,
                color = if (waterPercentage >= 0.5f) Color.White else darkWavesColor,
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(top = 10.dp)
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
            .height(330.dp),
            text = "1000"
        )
    }

}
