package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository

class DrinkUseCase(
    private val repository: HydrateRepository
) {

    suspend operator fun invoke(totalAmount: Int){
        repository.drink(totalAmount)
    }
}