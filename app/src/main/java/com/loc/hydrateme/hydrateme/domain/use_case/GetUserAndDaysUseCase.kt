package com.loc.hydrateme.hydrateme.domain.use_case

import com.loc.hydrateme.hydrateme.domain.model.UserAndDays
import com.loc.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow

class GetUserAndDaysUseCase(
    private val repository: HydrateRepository
) {

    suspend operator fun invoke(): Flow<UserAndDays> {
       return repository.getUserAndDays()
    }
}