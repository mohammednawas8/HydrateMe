package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.util.InsertHistoryRecord

data class UseCases(
    val insertUseUseCase: InsertUserUserCase,
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
    val updateCupSizeUseCase: UpdateCupSizeUseCase
)
