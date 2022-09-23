package com.loc.hydrateme.hydrateme.presentation.app_start_screens.splash_screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.loc.hydrateme.hydrateme.presentation.util.NavigationRoute

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavController,
) {
    LaunchedEffect(key1 = true) {
        viewModel.navigate.collect {
            Log.d("test",it.toString())
            if (it.homeScreen)
                navController.navigate(NavigationRoute.HomeScreen.route){
                    popUpTo(0)
                }
            else
                navController.navigate(NavigationRoute.IntroductionScreen.route){
                    popUpTo(0)
                }
        }
    }

}