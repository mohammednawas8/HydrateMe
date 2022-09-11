package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.model.Alarm
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository

class InsertAlarmUseCase(
    private val hydrateRepository: HydrateRepository
) {

    suspend operator fun invoke(alarm: Alarm){
        hydrateRepository.insertAlarm(alarm)
    }
}