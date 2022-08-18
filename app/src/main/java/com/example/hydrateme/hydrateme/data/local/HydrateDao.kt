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
}