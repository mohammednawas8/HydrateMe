package com.example.hydrateme.hydrateme.data.local

import androidx.room.*
import com.example.hydrateme.hydrateme.data.local.dto.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HydrateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDay(dayEntity: DayEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlarm(alarmEntity: AlarmEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlarms(alarmEntity: List<AlarmEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlarmDay(alarmDayEntity: AlarmDayEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlarmDays(alarmDayEntity: List<AlarmDayEntity>)

    @Query("SELECT * FROM UserEntity")
    fun getUserAndDays(): Flow<UserAndDaysOutput>

    @Query("SELECT * FROM UserEntity")
    fun getUser(): Flow<UserEntity>

    @Query("UPDATE UserEntity SET complete = :totalAmount")
    suspend fun updateComplete(totalAmount: Int)

    @Query("DELETE FROM DayEntity")
    suspend fun clearDayTable()

    @Query("DELETE FROM HistoryEntity")
    suspend fun clearHistoryTable()

    @Query("DELETE FROM AlarmEntity")
    suspend fun clearAlarmsTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryRecord(history: HistoryEntity)

    @Query("SELECT * FROM DayEntity ORDER BY day DESC LIMIT 1")
    fun getLastDay(): Flow<DayEntity?>

//    @Query("SELECT * FROM HistoryEntity WHERE day = :day")
//    fun getHistoryByTheDay(day: Long): Flow<List<HistoryEntity>>

    @Query("SELECT drinkAmount FROM HistoryEntity WHERE day =:day")
    fun getCompletedAmount(day: Long): Flow<List<Int>>

    @Transaction
    @Query("SELECT * FROM DayEntity ORDER BY day DESC LIMIT :dayDuration")
    fun getReportByDay(dayDuration: Int): Flow<List<DayEntityWithHistoryEntity>>

    @Query("UPDATE UserEntity SET cupSize = :cupSize")
    suspend fun updateCupSize(cupSize: Int)

    @Query("SELECT * FROM AlarmEntity WHERE userId =:userId")
    suspend fun getAlarms(userId: Int): List<AlarmEntity>

    @Query("SELECT * FROM AlarmEntity")
    fun getAlarmsAsFlow(): Flow<List<AlarmEntity>>

//    @Query ("UPDATE UserEntity set gender = :gender")
//    fun updateGender(gender: String)
//
//    @Query ("UPDATE UserEntity set weight = :weight")
//    fun updateWeight(weight: Int)
//
//    @Query ("UPDATE UserEntity set weightUnit = :weightUnit")
//    fun updateWeightUnit(weightUnit: String)
//
//    @Query ("UPDATE UserEntity set wakeUpHour = :wakeupHour")
//    fun updateWakeupHour(wakeupHour: String)
//
//    @Query ("UPDATE UserEntity set wakeUpMinutes = :wakeupMinutes")
//    fun updateWakeupMinutes(wakeupMinutes: String)
//
//    @Query ("UPDATE UserEntity set bedHour = :bedHour")
//    fun updateBedHour(bedHour: String)
//
//    @Query ("UPDATE UserEntity set bedMinutes = :bedMinutes")
//    fun updateBedMinutes(bedMinutes: String)

}