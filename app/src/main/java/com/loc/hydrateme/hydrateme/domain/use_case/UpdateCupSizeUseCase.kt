package com.loc.hydrateme.hydrateme.domain.use_case

import com.loc.hydrateme.hydrateme.domain.repository.HydrateRepository

class UpdateCupSizeUseCase(
    private val hydrateRepository: HydrateRepository
) {

    suspend operator fun invoke(cupSize: Int){
        hydrateRepository.updateCupSize(cupSize)
    }
}