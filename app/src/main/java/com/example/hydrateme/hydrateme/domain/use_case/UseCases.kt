package com.example.hydrateme.hydrateme.domain.use_case

data class UseCases(
    val insertUserUseCase: InsertUserUserCase,
    val insertHistoryUseCase: InsertHistoryUseCase,
    val getUserAndHistoryUseCase: GetUserAndHistoryUseCase
)
