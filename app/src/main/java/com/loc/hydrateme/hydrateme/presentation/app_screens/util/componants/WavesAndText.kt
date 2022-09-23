package com.loc.hydrateme.hydrateme.presentation.app_screens.util.componants

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loc.hydrateme.R

@Composable
fun WavesAndText(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.top_waves),
            contentDescription = "Waves",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )


        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            color = textColor,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 35.dp)
        )
    }
}

@Composable
@Preview
fun PreviewWavesAndText() {
    WavesAndText(text = "MONDAY, MAR 14", modifier = Modifier
        .height(100.dp)
        .fillMaxWidth())
}