package com.loc.hydrateme.hydrateme.domain.use_case

import com.loc.hydrateme.hydrateme.domain.repository.HydrateRepository

class ClearUserTable(
    private val hydrateRepository: HydrateRepository
) {

    suspend operator fun invoke(){
        hydrateRepository.clearUserTable()
    }
}