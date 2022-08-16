package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import com.example.hydrateme.hydrateme.domain.use_case.util.getMilliFromDate
import java.util.concurrent.TimeUnit

class GetLast10DaysReportUseCase(
    private val hydrateRepository: HydrateRepository
) {

//    suspend operator fun invoke(): Flow<List<Int>> {
//        val todayInMillis = getMilliFromDate()
//    }

    private fun getDaysAgoInMillis(days: Long): Long {
        val todayInMillis = getMilliFromDate()
        return todayInMillis - TimeUnit.DAYS.toMillis(days)
    }
}