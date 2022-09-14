package com.hydrate_me.hydrateme.hydrateme.domain.use_case

import com.hydrate_me.hydrateme.hydrateme.domain.model.Alarm
import com.hydrate_me.hydrateme.hydrateme.domain.repository.HydrateRepository

class InsertAlarmUseCase(
    private val hydrateRepository: HydrateRepository
) {

    suspend operator fun invoke(alarm: Alarm){
        hydrateRepository.insertAlarm(alarm)
    }
}