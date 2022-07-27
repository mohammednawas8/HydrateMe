package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.model.UserAndHistory
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow

class GetUserAndHistoryUseCase(
    private val repository: HydrateRepository
) {

    suspend operator fun invoke(): Flow<UserAndHistory> {
       return repository.getUserAndHistory()
    }
}