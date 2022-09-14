package com.hydrate_me.hydrateme.hydrateme.domain.use_case

import com.hydrate_me.hydrateme.hydrateme.domain.repository.HydrateRepository

class GetNotificationSoundUseCase(
    private val hydrateRepository: HydrateRepository
) {

    suspend operator fun invoke(): Int{
        return hydrateRepository.getNotificationSound()
    }
}