package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.model.History
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow

class GetHistoryByDayUseCase(
    private val hydrateRepository: HydrateRepository
) {

    suspend operator fun invoke(day: Long): Flow<List<History>>{
        return hydrateRepository.getHistoryByTheDay(day)
    }
}