package com.hydrate_me.hydrateme.hydrateme.domain.use_case

import com.hydrate_me.hydrateme.hydrateme.domain.model.UserAndDays
import com.hydrate_me.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow

class GetUserAndDaysUseCase(
    private val repository: HydrateRepository
) {

    suspend operator fun invoke(): Flow<UserAndDays> {
       return repository.getUserAndDays()
    }
}