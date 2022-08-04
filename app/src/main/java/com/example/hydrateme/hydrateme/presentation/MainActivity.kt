package com.example.hydrateme.hydrateme.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.hydrateme.hydrateme.presentation.app_screens.home_screen.HomeScreen
import com.example.hydrateme.ui.theme.HydrateMeTheme
import com.example.hydrateme.ui.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(color = Color.White)
            HydrateMeTheme {

                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = White
                ) {

                    HomeScreen()

//                    NavHost(
//                        navController = navController,
//                        startDestination = NavigationRoute.IntroductionScreen.route,
//                        modifier = Modifier.fillMaxSize()
//                    ) {
//
//                        composable(NavigationRoute.IntroductionScreen.route) {
//                            IntroductionScreen(navController = navController)
//                        }
//                        composable(NavigationRoute.GenderScreen.route) {
//                            GenderScreen(navController = navController,
//                                viewModel = appStartViewModel)
//                        }
//                        composable(NavigationRoute.WeightScreen.route) {
//                            WeightScreen(navController = navController,
//                                viewModel = appStartViewModel)
//                        }
//                        composable(NavigationRoute.WakeupScreen.route) {
//                            WakeUpScreen(navController = navController,
//                                viewModel = appStartViewModel)
//                        }
//                        composable(NavigationRoute.SleepScreen.route) {
//                            SleepScreen(navController = navController,
//                                viewModel = appStartViewModel)
//                        }
//                        composable(NavigationRoute.HomeScreen.route) {
//                            HomeScreen()
//                        }
//
//                    }
                }
            }
        }
    }
}

