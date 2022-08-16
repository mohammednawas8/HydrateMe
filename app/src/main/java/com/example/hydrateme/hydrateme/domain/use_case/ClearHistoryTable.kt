package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository

class ClearHistoryTable(
    private val hydrateRepository: HydrateRepository,
) {
    suspend operator fun invoke() {
        hydrateRepository.clearHistoryTable()
    }
}