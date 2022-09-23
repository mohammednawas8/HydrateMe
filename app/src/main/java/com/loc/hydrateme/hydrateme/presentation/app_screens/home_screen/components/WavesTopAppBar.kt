package com.loc.hydrateme.hydrateme.presentation.app_screens.home_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.loc.hydrateme.hydrateme.presentation.app_screens.util.componants.WavesAndText
import com.loc.hydrateme.R

@Composable
fun WavesTopAppBar(
    text: String,
    showBackIcon: Boolean = false,
    onBackClick: () -> Unit
) {
    Box {
        WavesAndText(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .height(152.dp)
        )

        if (showBackIcon) {
            IconButton(onClick = onBackClick,
                modifier = Modifier
                    .padding(start = 20.dp,top = 30.dp)
                    .size(20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "",
                    tint = Color.White,

                    )
            }
        }

    }
}