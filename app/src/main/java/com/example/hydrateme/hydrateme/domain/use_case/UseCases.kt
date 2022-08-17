package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.use_case.util.InsertHistoryRecord

data class UseCases(
    val insertUseUseCase: InsertUserUserCase,
    val insertDayUseCase: InsertDayUseCase,
    val getUseAndDaysUseCase: GetUserAndDaysUseCase,
    val getUserUseCase: GetUserUseCase,
    val drinkUseCase: DrinkUseCase,
    val getTodayReportUseCase: GetTodayReportUseCase,
    val getLast10DaysReportUseCase: GetLast10DaysReportUseCase,
    val clearDaysTable: ClearDaysTable,
    val clearHistoryTable: ClearHistoryTable,
    val insertHistoryRecord: InsertHistoryRecord,
    val getLastDayUseCase: GetLastDayUseCase,
    val getHistoryByDayUseCase: GetHistoryByDayUseCase,
    val getCompletedAmount: GetCompletedAmount
)
