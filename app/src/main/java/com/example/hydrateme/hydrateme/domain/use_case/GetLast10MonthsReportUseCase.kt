package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.model.History
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLast10MonthsReportUseCase(
    private val hydrateRepository: HydrateRepository

) {
    operator fun invoke(): Flow<List<History>>{
        return hydrateRepository.get10MonthsReport()
    }
}