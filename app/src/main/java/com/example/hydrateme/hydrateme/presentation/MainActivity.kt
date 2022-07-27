package com.example.hydrateme.hydrateme.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.componants.GradientButton
import com.example.hydrateme.ui.theme.HydrateMeTheme
import com.example.hydrateme.R


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HydrateMeTheme {

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        GradientButton(
                            text = "LES`T GO",
                            icon = painterResource(id = androidx.loader.R.drawable.notification_action_background),
                            modifier = Modifier
                                .width(260.dp)
                                .height(50.dp)
                        ) {

                        }

                    }
                    }


            }
        }
    }
}

