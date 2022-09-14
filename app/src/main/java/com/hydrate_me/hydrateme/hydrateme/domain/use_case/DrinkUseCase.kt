package com.hydrate_me.hydrateme.hydrateme.domain.use_case

import com.hydrate_me.hydrateme.hydrateme.domain.repository.HydrateRepository

class DrinkUseCase(
    private val repository: HydrateRepository
) {

    suspend operator fun invoke(totalAmount: Int){
        repository.drink(totalAmount)
    }
}