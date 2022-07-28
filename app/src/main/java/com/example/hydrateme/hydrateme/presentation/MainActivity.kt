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
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.hydrateme.ui.theme.HydrateMeTheme
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_start_screens.GenderScreen.GenderScreen
import com.example.hydrateme.hydrateme.presentation.app_start_screens.introduction_screen.IntroductionScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = Color.White)
            HydrateMeTheme {

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                ) {

                    GenderScreen(navController = rememberNavController())

                }


            }
        }
    }
}

