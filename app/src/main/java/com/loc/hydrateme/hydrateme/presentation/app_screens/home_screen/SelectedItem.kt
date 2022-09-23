package com.loc.hydrateme.hydrateme.presentation.app_screens.home_screen

sealed class SelectedItem() {
    object STATISTICS : SelectedItem()
    object ADD : SelectedItem()
    object SETTINGS : SelectedItem()
}