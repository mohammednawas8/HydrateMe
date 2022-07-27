package com.example.hydrateme.hydrateme.domain.repository

import com.example.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hydrateme.data.local.dto.UserEntity
import com.example.hydrateme.hydrateme.domain.model.UserAndHistory
import kotlinx.coroutines.flow.Flow

interface HydrateRepository {

    suspend fun insertUser(user: UserEntity)

    suspend fun insertHistory(history: HistoryEntity)

    suspend fun getUserAndHistory(): Flow<UserAndHistory>

}