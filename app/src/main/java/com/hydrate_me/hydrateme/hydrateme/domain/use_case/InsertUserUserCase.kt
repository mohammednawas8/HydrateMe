package com.hydrate_me.hydrateme.hydrateme.domain.use_case

import com.hydrate_me.hydrateme.hydrateme.data.local.dto.UserEntity
import com.hydrate_me.hydrateme.hydrateme.domain.repository.HydrateRepository

class InsertUserUserCase(
    private val repository: HydrateRepository,
) {

    suspend operator fun invoke(user: UserEntity) {
        repository.insertUser(user)
    }

}