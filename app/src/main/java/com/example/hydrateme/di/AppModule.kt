package com.example.hydrateme.di

import android.app.AlarmManager
import android.app.Application
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import com.example.hydrateme.hydrateme.data.alarm_manger.ReminderMangerImpl
import com.example.hydrateme.hydrateme.data.local.HydrateDao
import com.example.hydrateme.hydrateme.data.local.HydrateDatabase
import com.example.hydrateme.hydrateme.data.repository.HydrateRepositoryImpl
import com.example.hydrateme.hydrateme.domain.alarm_manger.ReminderManger
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import com.example.hydrateme.hydrateme.domain.use_case.*
import com.example.hydrateme.hydrateme.domain.util.InsertHistoryRecord
import com.example.hydrateme.hydrateme.presentation.app_start_screens.splash_screen.SplashViewModel.Companion.SPLASH_SCREEN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        dao: HydrateDao
    ): HydrateRepository = HydrateRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideUseCases(
        hydrateRepository: HydrateRepository,
        reminderManger: ReminderManger
    ) = UseCases(
        InsertUserUserCase(hydrateRepository),
        InsertDayUseCase(hydrateRepository),
        GetUserAndDaysUseCase(hydrateRepository),
        GetUserUseCase(hydrateRepository),
        DrinkUseCase(hydrateRepository),
        GetLast10DaysReportUseCase(hydrateRepository),
        ClearDaysTable(hydrateRepository),
        ClearHistoryTable(hydrateRepository),
        InsertHistoryRecord(hydrateRepository),
        GetLastDayUseCase(hydrateRepository),
        GetReportByDayUseCase(hydrateRepository),
        GetCompletedAmount(hydrateRepository),
        GetLast10WeeksReportUseCase(hydrateRepository),
        GetLast10MonthsReportUseCase(hydrateRepository),
        GetLast10YearsReportUseCase(hydrateRepository),
        UpdateCupSizeUseCase(hydrateRepository),
        SetInsertDayAlarmUseCase(),
        SaveReminderAlarmsUseCase(hydrateRepository),
        ClearAlarmsTableUseCase(hydrateRepository),
        ResetUserDataUseCase(hydrateRepository,reminderManger),
        GetAlarmsUseCase(hydrateRepository)
    )

    @Provides
    @Singleton
    fun provideResources(
        application: Application,
    ): Resources = application.resources

    @Provides
    @Singleton
    fun provideAlarmManger(
        application: Application
    ) = application.getSystemService(ALARM_SERVICE) as AlarmManager

    @Provides
    @Singleton
    fun provideReminderManger(
        @ApplicationContext context: Context,
        alarmManger:AlarmManager,
        dao: HydrateDao
    ): ReminderManger = ReminderMangerImpl(
        alarmManger, context,dao
    )

    @Provides
    @Singleton
    fun provideSplashSharedPreferences(
        application: Application
    ): SharedPreferences = application.getSharedPreferences(SPLASH_SCREEN, MODE_PRIVATE)
}