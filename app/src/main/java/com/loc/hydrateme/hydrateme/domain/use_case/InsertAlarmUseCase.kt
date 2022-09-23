package com.loc.hydrateme.hydrateme.domain.use_case

import com.loc.hydrateme.hydrateme.domain.model.Alarm
import com.loc.hydrateme.hydrateme.domain.repository.HydrateRepository

class InsertAlarmUseCase(
    private val hydrateRepository: HydrateRepository
) {

    suspend operator fun invoke(alarm: Alarm){
        hydrateRepository.insertAlarm(alarm)
    }
}