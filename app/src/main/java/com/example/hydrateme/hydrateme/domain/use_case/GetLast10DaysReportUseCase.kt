package com.example.hydrateme.hydrateme.domain.use_case

import android.util.Log
import com.example.hydrateme.hydrateme.data.mapper.toHistory
import com.example.hydrateme.hydrateme.domain.model.History
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import com.example.hydrateme.hydrateme.domain.use_case.util.getMilliFromDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit

class GetLast10DaysReportUseCase(
    private val hydrateRepository: HydrateRepository
) {

    suspend operator fun invoke(): Flow<List<Int>> {
        val todayInMillis = getMilliFromDate()

        //

        val reportMap = mutableMapOf<Int, MutableList<Int>>()
        hydrateRepository.getReport(todayInMillis, getDaysAgoInMillis(10)).map {
            it.forEach {
                if (it.time > getDaysAgoInMillis(1)) {
                    reportMap[0]?.add(it.toHistory().drinkedAmount)
                } else if (it.time < getDaysAgoInMillis(1) && it.time > getDaysAgoInMillis(2)) {
                    reportMap[1]?.add(it.toHistory().drinkedAmount)
                } else if (it.time < getDaysAgoInMillis(2) && it.time > getDaysAgoInMillis(3)) {
                    reportMap[2]?.add(it.toHistory().drinkedAmount)
                } else if (it.time < getDaysAgoInMillis(3) && it.time > getDaysAgoInMillis(4)) {
                    reportMap[3]?.add(it.toHistory().drinkedAmount)
                } else if (it.time < getDaysAgoInMillis(4) && it.time > getDaysAgoInMillis(5)) {
                    reportMap[5]?.add(it.toHistory().drinkedAmount)
                } else if (it.time < getDaysAgoInMillis(5) && it.time > getDaysAgoInMillis(6)) {
                    reportMap[6]?.add(it.toHistory().drinkedAmount)
                } else if (it.time < getDaysAgoInMillis(6) && it.time > getDaysAgoInMillis(7)) {
                    reportMap[7]?.add(it.toHistory().drinkedAmount)
                } else if (it.time < getDaysAgoInMillis(7) && it.time > getDaysAgoInMillis(8)) {
                    reportMap[8]?.add(it.toHistory().drinkedAmount)
                } else if (it.time < getDaysAgoInMillis(8) && it.time > getDaysAgoInMillis(9)) {
                    reportMap[9]?.add(it.toHistory().drinkedAmount)
                }
            }
        }
        val outputList = mutableListOf<Int>()
        reportMap.forEach {
            outputList.add(
                it.value.average().toInt().also {
                    Log.d("test",it.toString())
                }
            )
        }
        return MutableStateFlow(outputList)
    }


    private fun getDaysAgoInMillis(days: Long): Long {
        val todayInMillis = getMilliFromDate()
        return todayInMillis - TimeUnit.DAYS.toMillis(days)
    }
}