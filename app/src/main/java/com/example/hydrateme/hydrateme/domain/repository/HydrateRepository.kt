package com.example.hydrateme.hydrateme.domain.repository

import com.example.hydrateme.hydrateme.data.local.dto.DayEntity
import com.example.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hydrateme.data.local.dto.UserEntity
import com.example.hydrateme.hydrateme.domain.DayWithHistory
import com.example.hydrateme.hydrateme.domain.model.Day
import com.example.hydrateme.hydrateme.domain.model.History
import com.example.hydrateme.hydrateme.domain.model.Report
import kotlinx.coroutines.flow.Flow

interface HydrateRepository {

    suspend fun insertUser(user: UserEntity)

    suspend fun insertDay(day: DayEntity)

    suspend fun getUserAndDays(): Flow<Report>

    suspend fun getUser(): Flow<UserEntity>

    suspend fun drink(totalAmount: Int) // The total amount is the completed water amount

    suspend fun clearDayTable()

    suspend fun clearHistoryTable()

    suspend fun insertHistory(historyEntity: HistoryEntity)

    suspend fun getLastDay(): Flow<Day?>

    suspend fun getHistoryByTheDay(day: Long): Flow<List<History>>

   suspend fun getCompletedAmount(day: Long): Flow<List<Int>>

   suspend fun getReport(dayDuration: Int): Flow<Report>
}