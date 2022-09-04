package com.example.hydrateme.hydrateme.data.broadcast_receiver

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.hydrateme.HydrateApplication.Companion.DRINK_CHANNEL
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.data.alarm_manger.ReminderMangerImpl.Companion.ALARM_ID
import com.example.hydrateme.hydrateme.data.alarm_manger.ReminderMangerImpl.Companion.NEXT_ALARM
import com.example.hydrateme.hydrateme.domain.alarm_manger.ReminderManger
import com.example.hydrateme.hydrateme.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random

private val TAG = "DrinkReminderReceiver"

@AndroidEntryPoint
class DrinkWaterReminderReceiver : BroadcastReceiver() {
    @Inject lateinit var alarmManger: ReminderManger
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == REMINDER_RECEIVER_ACTION) {
            val nextDayAlarm = intent.getLongExtra(NEXT_ALARM,-1)
            val alarmId = intent.getIntExtra(ALARM_ID,-1)
            showDrinkNotification(context)
//            if(nextDayAlarm != -1L && alarmId != -1){
//                alarmManger.setNextDayReminders(nextDayAlarm, alarmId)
//                Log.d(TAG,"Next alarm on $nextDayAlarm")
//            }
        }
    }

    private fun showDrinkNotification(context: Context) {
        val intent = Intent(context, MainActivity::class.java).let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.getActivity(context, 0, it, PendingIntent.FLAG_IMMUTABLE)
            } else {
                PendingIntent.getActivity(context, 0, it, 0)
            }
        }
        val notification = NotificationCompat.Builder(
            context,
            DRINK_CHANNEL,
        ).setAutoCancel(true)
            .setContentTitle(context.getString(R.string.drink_notification_title))
            .setContentText(context.getString(R.string.drink_notification_text))
            .setContentIntent(intent)
            .setSmallIcon(R.drawable.ic_water_cup)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources,
                R.drawable.notification_larg_image))
            .build()

        NotificationManagerCompat.from(context).notify(Random.nextInt(), notification)
    }

    companion object {
        const val REMINDER_RECEIVER_ACTION = "ReminderReceiverAction"
    }

}