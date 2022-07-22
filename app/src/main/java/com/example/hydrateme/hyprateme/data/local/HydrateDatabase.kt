package com.example.hydrateme.hyprateme.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hydrateme.hyprateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hyprateme.data.local.dto.UserEntity

@Database(
    entities = [UserEntity::class, HistoryEntity::class],
    version = 1
)
abstract class HydrateDatabase : RoomDatabase() {

    abstract val dao: HydrateDao

}