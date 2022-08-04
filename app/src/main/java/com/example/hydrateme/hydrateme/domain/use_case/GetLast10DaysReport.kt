package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.data.mapper.toHistory
import com.example.hydrateme.hydrateme.domain.model.History
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import com.example.hydrateme.hydrateme.domain.use_case.util.getMilliFromDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit

class GetLast10DaysReport(
    private val hydrateRepository: HydrateRepository
) {

    suspend operator fun invoke(): Flow<List<History>> {
        val todayInMillis = getMilliFromDate()
        val dayAgo = todayInMillis - TimeUnit.DAYS.toMillis(1)

        val reportList = mutableListOf<History>()
         hydrateRepository.getReport(todayInMillis, dayAgo * 10).map {
            it.forEach{
                if(it.time > dayAgo){
                }
            }
        }
    }
}