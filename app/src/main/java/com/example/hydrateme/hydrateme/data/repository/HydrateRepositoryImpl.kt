package com.example.hydrateme.hydrateme.data.repository

import com.example.hydrateme.hydrateme.data.local.HydrateDao
import com.example.hydrateme.hydrateme.data.local.dto.DayEntity
import com.example.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hydrateme.data.local.dto.UserEntity
import com.example.hydrateme.hydrateme.data.mapper.toDay
import com.example.hydrateme.hydrateme.data.mapper.toHistory
import com.example.hydrateme.hydrateme.data.mapper.toUserAndDays
import com.example.hydrateme.hydrateme.domain.DayWithHistory
import com.example.hydrateme.hydrateme.domain.model.Day
import com.example.hydrateme.hydrateme.domain.model.History
import com.example.hydrateme.hydrateme.domain.model.Report
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class HydrateRepositoryImpl(
    val dao: HydrateDao,
) : HydrateRepository {

    override suspend fun insertUser(user: UserEntity) {
        dao.insertUser(user)
    }

    override suspend fun insertDay(day: DayEntity) {
        dao.insertDay(day)
    }

    override suspend fun getUserAndDays(): Flow<Report> {
        return dao.getUserAndDays().map {
            it.toUserAndDays()
        }
    }

    override suspend fun getUser(): Flow<UserEntity> {
        return dao.getUser()
    }

    override suspend fun drink(totalAmount: Int) {
        dao.updateComplete(totalAmount)
    }


    override suspend fun clearDayTable() {
        dao.clearDayTable()
    }

    override suspend fun clearHistoryTable() {
        dao.clearHistoryTable()
    }

    override suspend fun insertHistory(historyEntity: HistoryEntity) {
        dao.insertHistoryRecord(historyEntity)
    }

    override suspend fun getLastDay(): Flow<Day?> {
        return dao.getLastDay().map {
            it?.toDay()
        }
    }

    override suspend fun getHistoryByTheDay(day: Long): Flow<List<History>> {
        return dao.getHistoryByTheDay(day).map {
            it.map {
                it.toHistory()
            }
        }
    }

    override suspend fun getCompletedAmount(day: Long): Flow<List<Int>> {
        return dao.getCompletedAmount(day)
    }

    override suspend fun getReport(dayDuration: Int): Flow<DayWithHistory> {
        return dao.getReport(dayDuration).map {
            DayWithHistory(
                it.day.toDay(),
                it.historyEntity.map {
                    it.toHistory()
                }
            )
        }
    }
}