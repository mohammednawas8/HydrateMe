package com.loc.hydrateme.hydrateme.domain.use_case

import com.loc.hydrateme.hydrateme.domain.util.InsertHistoryRecord

data class UseCases(
    val insertUserUseCase: InsertUserUserCase,
    val insertDayUseCase: InsertDayUseCase,
    val getUseAndDaysUseCase: GetUserAndDaysUseCase,
    val getUserUseCase: GetUserUseCase,
    val drinkUseCase: DrinkUseCase,
    val getLast10DaysReportUseCase: GetLast10DaysReportUseCase,
    val clearDaysTable: ClearDaysTable,
    val clearHistoryTable: ClearHistoryTable,
    val insertHistoryRecord: InsertHistoryRecord,
    val getLastDayUseCase: GetLastDayUseCase,
    val getTodayReportUseCase: GetReportByDayUseCase,
    val getCompletedAmount: GetCompletedAmount,
    val getLast10WeeksReportUseCase: GetLast10WeeksReportUseCase,
    val getLast10MonthsReportUseCase: GetLast10MonthsReportUseCase,
    val getLast10YearsReportUseCase: GetLast10YearsReportUseCase,
    val updateCupSizeUseCase: UpdateCupSizeUseCase,
    val setInsertDayAlarmUseCase: SetInsertDayAlarmUseCase,
    val saveReminderAlarms: SaveReminderAlarmsUseCase,
    val clearAlarmsTableUseCase: ClearAlarmsTableUseCase,
    val resetUserDataUseCase: ResetUserDataUseCase,
    val getAlarms: GetAlarmsUseCase,
    val insertAlarmUseCase: InsertAlarmUseCase,
    val clearUserTable: ClearUserTable,
    val getNotificationSoundUseCase: GetNotificationSoundUseCase
)
