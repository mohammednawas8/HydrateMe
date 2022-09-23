package com.loc.hydrateme.hydrateme.data.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.loc.hydrateme.hydrateme.domain.alarm_manger.ReminderManger
import com.loc.hydrateme.hydrateme.domain.use_case.UseCases
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private val TAG = "RescheduleAlarms"
@AndroidEntryPoint
class RescheduleAlarmsReceiver: BroadcastReceiver() {
    @Inject lateinit var useCases: UseCases
    @Inject lateinit var reminderManger: ReminderManger

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.action?.let {
            if (
                it == Intent.ACTION_BOOT_COMPLETED ||
                it == Intent.ACTION_MY_PACKAGE_REPLACED
            ){
                Toast.makeText(context!!, TAG, Toast.LENGTH_SHORT).show()
                Log.d(TAG,"Reschedule Alarms")
                CoroutineScope(Dispatchers.IO).launch {
                    reminderManger.setInsertDayAlarm()
                }
                CoroutineScope(Dispatchers.IO).launch {
                    reminderManger.setDrinkReminders()
                }
            }
        }
    }
}