package com.example.hydrateme.hydrateme.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hydrateme.data.local.dto.UserEntity
import com.example.hydrateme.hydrateme.data.local.dto.UserAndHistoryOutput
import kotlinx.coroutines.flow.Flow

@Dao
interface HydrateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(historyEntity: HistoryEntity)

    @Query("SELECT * FROM UserEntity")
    fun getUserAndHistory(): Flow<UserAndHistoryOutput>

    @Query("SELECT * FROM UserEntity")
    fun getUser(): Flow<UserEntity>

    @Query("UPDATE UserEntity SET complete = :totalAmount")
    suspend fun updateComplete(totalAmount: Int)

    //Getting the history when time is greater or equal to today's
    // time and less than tomorrow's time
    @Query(
        """
        SELECT * FROM HistoryEntity
        WHERE id = 1
        AND time between :start and :end
    """
    )
     fun getReport(start: Long, end: Long): Flow<List<HistoryEntity>>

     @Query("DELETE FROM HistoryEntity")
     suspend fun deleteTable()

}