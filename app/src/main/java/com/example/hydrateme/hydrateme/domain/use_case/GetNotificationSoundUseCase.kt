package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow

class GetNotificationSoundUseCase(
    private val hydrateRepository: HydrateRepository
) {

    suspend operator fun invoke(): Int{
        return hydrateRepository.getNotificationSound()
    }
}