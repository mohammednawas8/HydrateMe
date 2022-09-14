package com.hydrate_me.hydrateme.hydrateme.domain.use_case

import com.hydrate_me.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCompletedAmount(
    private val hydrateRepository: HydrateRepository
) {

    suspend operator fun invoke(day: Long): Flow<Int>{
        return hydrateRepository.getCompletedAmount(day).map {
            it.sum()
        }
    }
}