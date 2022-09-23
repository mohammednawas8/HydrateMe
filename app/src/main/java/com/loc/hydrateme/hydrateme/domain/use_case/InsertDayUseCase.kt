package com.loc.hydrateme.hydrateme.domain.use_case

import com.loc.hydrateme.hydrateme.data.local.dto.DayEntity
import com.loc.hydrateme.hydrateme.domain.repository.HydrateRepository
import com.loc.hydrateme.hydrateme.presentation.util.yyyyMMddToMillis
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