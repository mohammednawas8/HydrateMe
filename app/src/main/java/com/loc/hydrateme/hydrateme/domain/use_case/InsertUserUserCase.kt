package com.loc.hydrateme.hydrateme.domain.use_case

import com.loc.hydrateme.hydrateme.data.local.dto.UserEntity
import com.loc.hydrateme.hydrateme.domain.repository.HydrateRepository

class InsertUserUserCase(
    private val repository: HydrateRepository,
) {

    suspend operator fun invoke(user: UserEntity) {
        repository.insertUser(user)
    }

}