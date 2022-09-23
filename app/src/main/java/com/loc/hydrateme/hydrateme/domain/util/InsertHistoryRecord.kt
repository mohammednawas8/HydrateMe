package com.loc.hydrateme.hydrateme.domain.util

import com.loc.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.loc.hydrateme.hydrateme.domain.repository.HydrateRepository

class InsertHistoryRecord(
    private val hydrateRepository: HydrateRepository
){
    suspend operator fun invoke(historyRecord: HistoryEntity){
        hydrateRepository.insertHistory(historyRecord)
    }
}