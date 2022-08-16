package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository

class ClearDaysTable(
    private val repository: HydrateRepository
) {

    suspend operator fun invoke(){
        repository.clearDayTable()
    }
}