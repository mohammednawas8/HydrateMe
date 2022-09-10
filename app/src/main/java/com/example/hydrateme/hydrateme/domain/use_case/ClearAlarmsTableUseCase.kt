package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository

class ClearAlarmsTableUseCase(
    private val hydrateRepository: HydrateRepository
) {

    suspend operator fun invoke(){
        hydrateRepository.clearAlarmsTable()
    }
}