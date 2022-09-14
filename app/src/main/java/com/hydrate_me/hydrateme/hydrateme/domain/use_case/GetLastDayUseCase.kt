package com.hydrate_me.hydrateme.hydrateme.domain.use_case

import com.hydrate_me.hydrateme.hydrateme.domain.model.Day
import com.hydrate_me.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow

class GetLastDayUseCase(
    private val hydrateRepository: HydrateRepository,
) {

    suspend operator fun invoke(): Flow<Day?> {
        return hydrateRepository.getLastDay()
    }

}