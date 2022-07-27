package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.data.local.dto.UserEntity
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository

class InsertUserUserCase(
    private val repository: HydrateRepository,
) {

    suspend operator fun invoke(user: UserEntity) {
        repository.insertUser(user)
    }

}