package com.loc.hydrateme.hydrateme.domain.use_case

import com.loc.hydrateme.hydrateme.domain.model.Alarm
import com.loc.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow

class GetAlarmsUseCase(
    private val hydrateRepository: HydrateRepository
) {
    suspend operator fun invoke(): Flow<List<Alarm>>{
        return hydrateRepository.getAlarmsAsFlow()
    }
}