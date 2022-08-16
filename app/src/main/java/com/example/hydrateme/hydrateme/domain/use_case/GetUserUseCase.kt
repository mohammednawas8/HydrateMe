package com.example.hydrateme.hydrateme.domain.use_case

import android.util.Log
import com.example.hydrateme.hydrateme.data.mapper.toUser
import com.example.hydrateme.hydrateme.domain.model.User
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUserUseCase(
    private val repository: HydrateRepository,
) {
    suspend operator fun invoke(): Flow<User> {
        return repository.getUser().map {
            it.toUser()
        }
    }
}