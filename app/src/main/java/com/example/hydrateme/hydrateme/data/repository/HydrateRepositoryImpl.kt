package com.example.hydrateme.hydrateme.data.repository

import com.example.hydrateme.hydrateme.data.local.HydrateDao
import com.example.hydrateme.hydrateme.data.local.dto.DayEntity
import com.example.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hydrateme.data.local.dto.UserEntity
import com.example.hydrateme.hydrateme.data.mapper.toDay
import com.example.hydrateme.hydrateme.data.mapper.toHistory
import com.example.hydrateme.hydrateme.data.mapper.toReport
import com.example.hydrateme.hydrateme.data.mapper.toUserAndDays
import com.example.hydrateme.hydrateme.domain.model.Day
import com.example.hydrateme.hydrateme.domain.model.History
import com.example.hydrateme.hydrateme.domain.model.Report
import com.example.hydrateme.hydrateme.domain.model.UserAndDays
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class HydrateRepositoryImpl(
    val dao: HydrateDao,
) : HydrateRepository {

    override suspend fun insertUser(user: UserEntity) {
        dao.insertUser(user)
    }

    override suspend fun insertDay(day: DayEntity) {
        dao.insertDay(day)
    }

    override suspend fun getUserAndDays(): Flow<UserAndDays> {
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

    override fun getTodayReport(daysDuration: Int): Flow<List<History>> {
        return dao.getReportByDay(daysDuration).map {
            it.map {
                if (it.historyEntity.isNotEmpty())
                    it.historyEntity.first().toHistory()
                else
                    History(
                        0,
                        0
                    )
            }
        }
    }

    override fun get10DaysReport(): Flow<List<History>> {
        return dao.getReportByDay(10).map {
            it.mapIndexed { i, dayEntityWithHistoryEntity ->
                val sum =
                    dayEntityWithHistoryEntity.historyEntity.sumOf { it.drinkAmount } //Sum of day drinks
                var time = 0L
                dayEntityWithHistoryEntity.historyEntity.forEach {
                    time = it.time
                }
                History(
                    time,
                    sum,
                )
            }
        }
//        return dao.getReportByDay(10).map {
//            it.map {
//                it.historyEntity.sumOf { it.drinkAmount }
//            }
//        }
    }


    override suspend fun getCompletedAmount(day: Long): Flow<List<Int>> {
        return dao.getCompletedAmount(day)
    }
}