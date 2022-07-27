package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository

class InsertHistoryUseCase(
    private val repository: HydrateRepository
) {

    suspend operator fun invoke(history: HistoryEntity){
        repository.insertHistory(history)
    }

}