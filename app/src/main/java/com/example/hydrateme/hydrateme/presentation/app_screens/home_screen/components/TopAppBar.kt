package com.example.hydrateme.hydrateme.presentation.app_screens.home_screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hydrateme.hydrateme.presentation.app_screens.util.componants.WavesAndText

@Composable
fun TopAppBar(
    text: String
) {
        WavesAndText(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .height(152.dp)
        )
}