package com.example.hydrateme.di

import android.app.Application
import android.content.res.Resources
import com.example.hydrateme.hydrateme.data.local.HydrateDao
import com.example.hydrateme.hydrateme.data.local.HydrateDatabase
import com.example.hydrateme.hydrateme.data.repository.HydrateRepositoryImpl
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import com.example.hydrateme.hydrateme.domain.use_case.*
import com.example.hydrateme.hydrateme.domain.use_case.util.InsertHistoryRecord
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
        return HydrateDatabase.getInstance(application).dao
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
        InsertDayUseCase(hydrateRepository),
        GetUserAndDaysUseCase(hydrateRepository),
        GetUserUseCase(hydrateRepository),
        DrinkUseCase(hydrateRepository),
        GetTodayReportUseCase(hydrateRepository),
        GetLast10DaysReportUseCase(hydrateRepository),
        ClearDaysTable(hydrateRepository),
        ClearHistoryTable(hydrateRepository),
        InsertHistoryRecord(hydrateRepository),
        GetLastDayUseCase(hydrateRepository),
        GetHistoryByDayUseCase(hydrateRepository),
        GetCompletedAmount(hydrateRepository)
    )

    @Provides
    @Singleton
    fun provideResources(
        application: Application,
    ): Resources = application.resources

}