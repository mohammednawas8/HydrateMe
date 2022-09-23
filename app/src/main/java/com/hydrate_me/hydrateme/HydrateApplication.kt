package com.hydrate_me.hydrateme

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentResolver
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import com.hydrate_me.hydrateme.hydrateme.domain.use_case.UseCases
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltAndroidApp
class HydrateApplication : Application() {

    companion object {
        const val DRINK_CHANNEL = "DrinkChannel"
    }

    @Inject
    lateinit var notificationManager: NotificationManager

    @Inject
    lateinit var useCases: UseCases

    private lateinit var job: Job

    override fun onCreate() {
        super.onCreate()
        job = CoroutineScope(Dispatchers.IO).launch {
            val id = useCases.getNotificationSoundUseCase.invoke()

            val alarmSound = Uri.parse("android.resource://" + packageName
                .toString() + "/" + id)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    DRINK_CHANNEL,
                    resources.getString(R.string.drink_notification_channel),
                    NotificationManager.IMPORTANCE_DEFAULT
                )

                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build()
                channel.setSound(null, null)
                notificationManager.createNotificationChannel(channel)
            }
        }

    }

    override fun onTerminate() {
        super.onTerminate()
        job.cancel()
    }

}