package com.example.hydrateme

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HydrateApplication : Application() {
    companion object {
        const val DRINK_CHANNEL = "DrinkChannel"
    }

    override fun onCreate() {
        super.onCreate()

        createDrinkNotification()
    }

    private fun createDrinkNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                DRINK_CHANNEL,
                getString(R.string.drink_notification_channel),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}