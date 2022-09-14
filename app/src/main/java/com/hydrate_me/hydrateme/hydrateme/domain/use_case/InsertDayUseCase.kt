package com.hydrate_me.hydrateme.hydrateme.domain.use_case

import com.hydrate_me.hydrateme.hydrateme.data.local.dto.DayEntity
import com.hydrate_me.hydrateme.hydrateme.domain.repository.HydrateRepository
import com.hydrate_me.hydrateme.hydrateme.presentation.util.yyyyMMddToMillis
import java.text.SimpleDateFormat
import java.util.*

class InsertDayUseCase(
    private val repository: HydrateRepository,
) {

    suspend operator fun invoke(): String {
        val pattern = "yyyy-MM-dd"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date = simpleDateFormat.format(Date())
        repository.insertDay(DayEntity(
            yyyyMMddToMillis(date),
            1
        ))
        return date
    }

}