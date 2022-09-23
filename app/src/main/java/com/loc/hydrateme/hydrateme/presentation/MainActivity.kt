package com.loc.hydrateme.hydrateme.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.loc.hydrateme.hydrateme.presentation.app_screens.home_screen.HomeScreen
import com.loc.hydrateme.hydrateme.presentation.app_start_screens.gender_screen.GenderScreen
import com.loc.hydrateme.hydrateme.presentation.app_start_screens.introduction_screen.IntroductionScreen
import com.loc.hydrateme.hydrateme.presentation.app_start_screens.sleep_screen.SleepScreen
import com.loc.hydrateme.hydrateme.presentation.app_start_screens.splash_screen.SplashScreen
import com.loc.hydrateme.hydrateme.presentation.app_start_screens.util.AppStartViewModel
import com.loc.hydrateme.hydrateme.presentation.app_start_screens.wakeup_screen.WakeUpScreen
import com.loc.hydrateme.hydrateme.presentation.app_start_screens.weight_screen.WeightScreen
import com.loc.hydrateme.hydrateme.presentation.util.NavigationRoute
import com.loc.hydrateme.ui.theme.HydrateMeTheme
import com.loc.hydrateme.ui.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

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

                    val navController = rememberNavController()
                    val appStartViewModel by viewModels<AppStartViewModel>()

                    NavHost(
                        navController = navController,
                        startDestination = NavigationRoute.SplashScreen.route,
                        modifier = Modifier.fillMaxSize()
                    ) {

                        composable(NavigationRoute.SplashScreen.route){
                            SplashScreen(navController = navController)
                        }

                        composable(NavigationRoute.IntroductionScreen.route) {
                            IntroductionScreen(navController = navController)
                        }
                        composable(NavigationRoute.GenderScreen.route) {
                            GenderScreen(navController = navController,
                                viewModel = appStartViewModel)
                        }
                        composable(NavigationRoute.WeightScreen.route) {
                            WeightScreen(navController = navController,
                                viewModel = appStartViewModel)
                        }
                        composable(NavigationRoute.WakeupScreen.route) {
                            WakeUpScreen(navController = navController,
                                viewModel = appStartViewModel)
                        }
                        composable(NavigationRoute.SleepScreen.route) {
                            SleepScreen(navController = navController,
                                viewModel = appStartViewModel)
                        }
                        composable(NavigationRoute.HomeScreen.route) {
                            HomeScreen()
                        }
                    }
                }
            }
        }
    }
}

