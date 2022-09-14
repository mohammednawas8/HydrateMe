package com.hydrate_me.hydrateme.hydrateme.domain.use_case

import com.hydrate_me.hydrateme.hydrateme.domain.model.History
import com.hydrate_me.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow

class GetLast10MonthsReportUseCase(
    private val hydrateRepository: HydrateRepository

) {
    operator fun invoke(): Flow<List<History>>{
        return hydrateRepository.get10MonthsReport()
    }
}