package com.example.hydrateme.hydrateme.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hydrateme.ui.theme.HydrateMeTheme
import com.example.hydrateme.hydrateme.presentation.app_start_screens.gender_screen.GenderScreen
import com.example.hydrateme.hydrateme.presentation.app_start_screens.introduction_screen.IntroductionScreen
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.AppStartViewModel
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.componants.ScrollPicker
import com.example.hydrateme.hydrateme.presentation.util.NavigationRoute
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

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        ScrollPicker(leftList = listOf("","50","55","60","65","70","75","80",""),
                            rightList = listOf("kg","ib",""),
                            onLeftValueChange = {

                            },
                            onRightValueChange = {

                            },
                            modifier = Modifier.height(160.dp).width(152.dp)
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
//                            GenderScreen(navController = navController,appStartViewModel)
//                        }
//                        composable(NavigationRoute.WeightScreen.route){
//
//                        }
//                        composable(NavigationRoute.WakeupScreen.route){
//
//                        }
//                        composable(NavigationRoute.SleepScreen.route){
//
//                        }
//
//                    }
                }
            }
        }
    }
}

