package com.example.hydrateme.hydrateme.data.alarm_manger

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.example.hydrateme.hydrateme.data.broadcast_receiver.DrinkWaterReminderReceiver
import com.example.hydrateme.hydrateme.data.broadcast_receiver.DrinkWaterReminderReceiver.Companion.REMINDER_RECEIVER_ACTION
import com.example.hydrateme.hydrateme.data.broadcast_receiver.InsertDayReceiver
import com.example.hydrateme.hydrateme.data.local.HydrateDao
import com.example.hydrateme.hydrateme.domain.alarm_manger.ReminderManger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit

private val TAG = "ReminderMangerImpl"

class ReminderMangerImpl(
    private val alarmManger: AlarmManager,
    private val context: Context,
    private val dao: HydrateDao,
) : ReminderManger {

    companion object {
        const val NEXT_ALARM = "NextAlarm"
        const val ALARM_ID = "AlarmId"
        const val ALARM_PASSED = "Alarm_passed"
    }

    //TODO: Fix alarms don't get saved in the database bug, Example : WakeupTime: 11:00, sleepTime: 1:30
    // Basic solution is by counting the time different in a forward way using a loop
    // Delayed alarms are not triggered
    override fun setDrinkReminders() {
        CoroutineScope(Dispatchers.IO).launch {
            val alarmsList = dao.getAlarms(1)
            alarmsList.forEach { alarmEntity ->
                val calendar = Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    set(Calendar.HOUR_OF_DAY, alarmEntity.hour)
                    set(Calendar.MINUTE, alarmEntity.minute)
                }
                val intent = Intent(context, DrinkWaterReminderReceiver::class.java).apply {
                    putExtra(NEXT_ALARM, calendar.timeInMillis + TimeUnit.DAYS.toMillis(1))
                    putExtra(ALARM_ID, alarmEntity.alarmId)
                    action = REMINDER_RECEIVER_ACTION
                }

                // Check if the Calendar time is in the past, if so we will handle it in the DrinkWaterReminderReceiver
                if (calendar.timeInMillis < System.currentTimeMillis()) {
                    intent.putExtra(ALARM_PASSED,true)
                    Log.d(TAG, "Alarm is passed ${alarmEntity.hour}:${alarmEntity.minute}")
                }

                val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    PendingIntent.getBroadcast(context,
                        alarmEntity.alarmId,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE)
                } else {
                    PendingIntent.getBroadcast(context, alarmEntity.alarmId, intent, 0)
                }

                alarmManger.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                ).also { Log.d(TAG, "Alarm set at ${alarmEntity.hour}:${alarmEntity.minute}") }
            }
        }
    }

    override fun setNextDayReminders(time: Long, alarmId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val intent = Intent(context, DrinkWaterReminderReceiver::class.java).apply {
                putExtra(NEXT_ALARM, time + TimeUnit.DAYS.toMillis(1))
                action = REMINDER_RECEIVER_ACTION
            }
            val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.getBroadcast(context, alarmId, intent, PendingIntent.FLAG_IMMUTABLE)
            } else {
                PendingIntent.getBroadcast(context, alarmId, intent, 0)
            }
            alarmManger.setExact(
                AlarmManager.RTC_WAKEUP,
                time,
                pendingIntent
            )
        }
    }

    override fun setInsertDayAlarm() {
        val intent = Intent(context, InsertDayReceiver::class.java)
        intent.action = InsertDayReceiver.ADD_DAY_RECEIVER
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getBroadcast(context,
                InsertDayReceiver.ADD_DAY_REQUEST_CODE, intent,
                PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getBroadcast(context, InsertDayReceiver.ADD_DAY_REQUEST_CODE, intent, 0)
        }

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 12)
        }

        alarmManger.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    override fun deleteDrinkReminder() {
    }

    override fun updateDrinkReminder() {
    }


}