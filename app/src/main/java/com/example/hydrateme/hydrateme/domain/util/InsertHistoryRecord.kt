package com.example.hydrateme.hydrateme.domain.util

import com.example.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository

class InsertHistoryRecord(
    private val hydrateRepository: HydrateRepository
){
    suspend operator fun invoke(historyRecord: HistoryEntity){
        hydrateRepository.insertHistory(historyRecord)
    }
}