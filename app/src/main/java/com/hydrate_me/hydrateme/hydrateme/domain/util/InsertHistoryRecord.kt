package com.hydrate_me.hydrateme.hydrateme.domain.util

import com.hydrate_me.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.hydrate_me.hydrateme.hydrateme.domain.repository.HydrateRepository

class InsertHistoryRecord(
    private val hydrateRepository: HydrateRepository
){
    suspend operator fun invoke(historyRecord: HistoryEntity){
        hydrateRepository.insertHistory(historyRecord)
    }
}