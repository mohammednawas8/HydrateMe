package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository

class DeleteHistoryTable(
    private val repository: HydrateRepository
) {

    suspend operator fun invoke(){
        repository.deleteHistoryTable()
    }
}