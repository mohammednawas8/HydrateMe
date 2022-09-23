package com.loc.hydrateme.hydrateme.domain.use_case

import com.loc.hydrateme.hydrateme.domain.repository.HydrateRepository

class ClearDaysTable(
    private val repository: HydrateRepository
) {

    suspend operator fun invoke(){
        repository.clearDayTable()
    }
}