package com.example.hydrateme.hydrateme.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hydrateme.hydrateme.data.local.dto.*

@Database(
    entities = [UserEntity::class, DayEntity::class, HistoryEntity::class, AlarmEntity::class, AlarmDayEntity::class],
    version = 9
)
abstract class HydrateDatabase : RoomDatabase() {

    abstract val dao: HydrateDao


    companion object {

        @Volatile
        private var INSTANCE: HydrateDatabase? = null
        fun getInstance(application: Application): HydrateDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    application,
                    HydrateDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }

        const val DATABASE_NAME = "HydrateDatabase"
    }
}