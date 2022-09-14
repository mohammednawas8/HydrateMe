package com.example.hydrateme.hydrateme.domain.repository

import com.example.hydrateme.hydrateme.data.local.dto.DayEntity
import com.example.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hydrateme.data.local.dto.UserEntity
import com.example.hydrateme.hydrateme.domain.model.*
import kotlinx.coroutines.flow.Flow

interface HydrateRepository {

    suspend fun insertUser(user: UserEntity)

    suspend fun insertDay(day: DayEntity)

    suspend fun getUserAndDays(): Flow<UserAndDays>

    suspend fun getUser(): Flow<UserEntity>

    suspend fun drink(totalAmount: Int) // The total amount is the completed water amount

    suspend fun clearDayTable()

    suspend fun clearHistoryTable()

    suspend fun clearAlarmsTable()

    suspend fun insertHistory(historyEntity: HistoryEntity)

    suspend fun getLastDay(): Flow<Day?>

    suspend fun getCompletedAmount(day: Long): Flow<List<Int>>

    fun getTodayReport(daysDuration: Int): Flow<List<History>>

    fun get10DaysReport(): Flow<List<History>>

    fun get10WeeksReport(): Flow<List<History>>

    fun get10MonthsReport(): Flow<List<History>>

    fun get10YearsReport(): Flow<List<History>>

    suspend fun updateCupSize(cupSize: Int)

    suspend fun insertAlarm(alarm: Alarm)

    suspend fun insertAlarms(alarms: List<Alarm>)

    suspend fun getAlarms(userId: Int): List<Alarm>

    suspend fun insertAlarmDay(alarmDay: AlarmDay,alarmId: Int)

    suspend fun getAlarmsAsFlow(): Flow<List<Alarm>>

    suspend fun clearUserTable()

    suspend fun getNotificationSound(): Int
}