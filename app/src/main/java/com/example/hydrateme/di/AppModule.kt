package com.example.hydrateme.di

import android.app.Application
import androidx.room.Room
import com.example.hydrateme.hydrateme.data.local.HydrateDao
import com.example.hydrateme.hydrateme.data.local.HydrateDatabase
import com.example.hydrateme.hydrateme.data.local.HydrateDatabase.Companion.DATABASE_NAME
import com.example.hydrateme.hydrateme.data.repository.HydrateRepositoryImpl
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import com.example.hydrateme.hydrateme.domain.use_case.GetUserAndHistoryUseCase
import com.example.hydrateme.hydrateme.domain.use_case.InsertHistoryUseCase
import com.example.hydrateme.hydrateme.domain.use_case.InsertUserUserCase
import com.example.hydrateme.hydrateme.domain.use_case.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabaseDao(application: Application): HydrateDao {
        return Room.databaseBuilder(
            application,
            HydrateDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build().dao
    }

    @Provides
    @Singleton
    fun provideHydrateRepository(
        dao: HydrateDao,
    ): HydrateRepository = HydrateRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideUseCases(
        hydrateRepository: HydrateRepository,
    ) = UseCases(
        InsertUserUserCase(hydrateRepository),
        InsertHistoryUseCase(hydrateRepository),
        GetUserAndHistoryUseCase(hydrateRepository)
    )

}