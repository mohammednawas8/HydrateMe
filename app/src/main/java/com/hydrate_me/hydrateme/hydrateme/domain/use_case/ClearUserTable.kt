package com.hydrate_me.hydrateme.hydrateme.domain.use_case

import com.hydrate_me.hydrateme.hydrateme.domain.repository.HydrateRepository

class ClearUserTable(
    private val hydrateRepository: HydrateRepository
) {

    suspend operator fun invoke(){
        hydrateRepository.clearUserTable()
    }
}