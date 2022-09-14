package com.hydrate_me.hydrateme.hydrateme.domain.use_case

import com.hydrate_me.hydrateme.hydrateme.domain.model.Alarm
import com.hydrate_me.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow

class GetAlarmsUseCase(
    private val hydrateRepository: HydrateRepository
) {
    suspend operator fun invoke(): Flow<List<Alarm>>{
        return hydrateRepository.getAlarmsAsFlow()
    }
}