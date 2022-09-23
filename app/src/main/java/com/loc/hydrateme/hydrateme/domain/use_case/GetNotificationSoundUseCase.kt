package com.loc.hydrateme.hydrateme.domain.use_case

import com.loc.hydrateme.hydrateme.domain.repository.HydrateRepository

class GetNotificationSoundUseCase(
    private val hydrateRepository: HydrateRepository
) {

    suspend operator fun invoke(): Int{
        return hydrateRepository.getNotificationSound()
    }
}