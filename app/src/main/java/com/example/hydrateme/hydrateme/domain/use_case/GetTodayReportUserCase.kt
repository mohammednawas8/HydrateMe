package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import com.example.hydrateme.hydrateme.domain.use_case.util.getMilliFromDate
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit

class GetTodayReportUserCase(
    private val repository: HydrateRepository
) {

    suspend operator fun invoke(): Flow<List<HistoryEntity>>{
        val todayInMillis = getMilliFromDate()
        val tomorrowInMillis = todayInMillis + TimeUnit.DAYS.toMillis(1)
        return repository.getReport(todayInMillis,todayInMillis + tomorrowInMillis)
    }


}