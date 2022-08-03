package com.example.hydrateme.hydrateme.data.repository

import com.example.hydrateme.hydrateme.data.local.HydrateDao
import com.example.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hydrateme.data.local.dto.UserEntity
import com.example.hydrateme.hydrateme.data.mapper.toUserAndHistory
import com.example.hydrateme.hydrateme.domain.model.UserAndHistory
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HydrateRepositoryImpl(
    val dao: HydrateDao
): HydrateRepository {

    override suspend fun insertUser(user: UserEntity) {
        dao.insertUser(user)
    }

    override suspend fun insertHistory(history: HistoryEntity) {
        dao.insertHistory(history)
    }

    override suspend fun getUserAndHistory(): Flow<UserAndHistory> {
        return dao.getUserAndHistory().map {
            it.toUserAndHistory()
        }
    }

    override suspend fun getUser(): Flow<UserEntity> {
        return dao.getUser()
    }
}