package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository

class UpdateCupSizeUseCase(
    private val hydrateRepository: HydrateRepository
) {

    suspend operator fun invoke(cupSize: Int){
        hydrateRepository.updateCupSize(cupSize)
    }
}