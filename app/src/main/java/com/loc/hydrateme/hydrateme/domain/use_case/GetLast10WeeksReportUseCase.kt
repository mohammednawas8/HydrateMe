package com.loc.hydrateme.hydrateme.domain.use_case

import com.loc.hydrateme.hydrateme.domain.model.History
import com.loc.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow

class GetLast10WeeksReportUseCase(
    private val hydrateRepository: HydrateRepository,
) {
    operator fun invoke(): Flow<List<History>> {
        return hydrateRepository.get10WeeksReport()
    }
}