package com.example.hydrateme.hyprateme.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hydrateme.hyprateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hyprateme.data.local.dto.UserEntity
import com.example.hydrateme.hyprateme.data.local.dto.UserAndHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface HydrateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(historyEntity: HistoryEntity)

    @Query("SELECT * FROM UserEntity")
    fun getUserAndHistory(): Flow<UserAndHistory>
}