package com.example.hydrateme.hydrateme.data.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.hydrateme.hydrateme.domain.use_case.UseCases
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private val TAG = "InsertDayReceiver"
@AndroidEntryPoint
class InsertDayReceiver : BroadcastReceiver() {
    @Inject
    lateinit var useCases: UseCases

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ADD_DAY_RECEIVER) {
            CoroutineScope(Dispatchers.IO).launch {
                useCases.insertDayUseCase.invoke()
                Log.d(TAG,"Added New Day")
            }
        }

    }

    companion object {
        const val ADD_DAY_RECEIVER = "AddDayReceiver"
        const val ADD_DAY_REQUEST_CODE = 57
    }

}