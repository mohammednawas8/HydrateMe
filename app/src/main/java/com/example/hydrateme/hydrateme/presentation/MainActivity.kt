package com.example.hydrateme.hydrateme.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hydrateme.hydrateme.presentation.app_screens.components.WaterBottle
import com.example.hydrateme.ui.theme.HydrateMeTheme
import com.example.hydrateme.hydrateme.presentation.app_start_screens.gender_screen.GenderScreen
import com.example.hydrateme.hydrateme.presentation.app_start_screens.introduction_screen.IntroductionScreen
import com.example.hydrateme.hydrateme.presentation.app_start_screens.sleep_screen.SleepScreen
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.AppStartViewModel
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.componants.ScrollPicker
import com.example.hydrateme.hydrateme.presentation.app_start_screens.wakeup_screen.WakeUpScreen
import com.example.hydrateme.hydrateme.presentation.app_start_screens.weight_screen.WeightScreen
import com.example.hydrateme.hydrateme.presentation.util.NavigationRoute
import com.example.hydrateme.ui.theme.WhiteBlue
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

                    val navController = rememberNavController()
                    val appStartViewModel = viewModels<AppStartViewModel>().value



                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)) {
                        WaterBottle(
                            waterPercentage = 0.6f,
                            modifier = Modifier
                                .width(150.dp)
                                .height(335.dp).padding(start = 10.dp),
                            text = "1000"
                        )
                    }


//                    NavHost(
//                        navController = navController,
//                        startDestination = NavigationRoute.IntroductionScreen.route,
//                        modifier = Modifier.fillMaxSize()
//                    ){
//
//                        composable(NavigationRoute.IntroductionScreen.route){
//                            IntroductionScreen(navController = navController)
//                        }
//                        composable(NavigationRoute.GenderScreen.route){
//                            GenderScreen(navController = navController, viewModel = appStartViewModel)
//                        }
//                        composable(NavigationRoute.WeightScreen.route){
//                            WeightScreen(navController = navController, viewModel = appStartViewModel)
//                        }
//                        composable(NavigationRoute.WakeupScreen.route){
//                            WakeUpScreen(navController = navController, viewModel = appStartViewModel)
//                        }
//                        composable(NavigationRoute.SleepScreen.route){
//                            SleepScreen(navController = navController, viewModel = appStartViewModel)
//                        }
//                        composable(NavigationRoute.HomeScreen.route){
//
//                        }

                }
            }
        }
    }
}
//}

