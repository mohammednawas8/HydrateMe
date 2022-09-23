package com.loc.hydrateme.hydrateme.domain.use_case

import com.loc.hydrateme.hydrateme.domain.repository.HydrateRepository

class DrinkUseCase(
    private val repository: HydrateRepository
) {

    suspend operator fun invoke(totalAmount: Int){
        repository.drink(totalAmount)
    }
}