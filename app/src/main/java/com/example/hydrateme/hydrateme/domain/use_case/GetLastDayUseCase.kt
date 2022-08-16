package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.model.Day
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow

class GetLastDayUseCase(
    private val hydrateRepository: HydrateRepository,
) {

    suspend operator fun invoke(): Flow<Day?> {
        return hydrateRepository.getLastDay()
    }

}