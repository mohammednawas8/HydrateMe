package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.model.History
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import com.example.hydrateme.hydrateme.domain.use_case.util.getMilliFromDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit

class GetLast10DaysReportUseCase(
    private val hydrateRepository: HydrateRepository
) {
     operator fun invoke(): Flow<List<History>> {
        return hydrateRepository.get10DaysReport()
    }
}