package com.loc.hydrateme.hydrateme.presentation.app_start_screens.splash_screen

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {

    private val _navigate = MutableSharedFlow<SplashScreenStates>(1)
    val navigate = _navigate.asSharedFlow()

    companion object {
        const val SPLASH_SCREEN = "Splash_Screen"
        const val IS_DATABASE_EMPTY = "Navigation_Shared_Pref"
    }

    init {
        val isDatabaseEmpty = sharedPreferences.getBoolean(IS_DATABASE_EMPTY, true)
        viewModelScope.launch {
            _navigate.emit(SplashScreenStates(isDatabaseEmpty, !isDatabaseEmpty))
        }
    }


}