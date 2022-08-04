package com.example.hydrateme.hydrateme.domain.use_case

data class UseCases(
    val insertUseUseCase: InsertUserUserCase,
    val insertHistoryUseCase: InsertHistoryUseCase,
    val getUseAndHistoryUseCase: GetUserAndHistoryUseCase,
    val getUseUseCase: GetUserUseCase,
    val drinkUseCase: DrinkUseCase,
    val getTodayReportUseCase: GetTodayReportUserCase
)
