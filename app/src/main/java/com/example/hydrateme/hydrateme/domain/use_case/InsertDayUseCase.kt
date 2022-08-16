package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.data.local.dto.DayEntity
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import com.example.hydrateme.hydrateme.presentation.util.yyyyMMddToMillis
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