package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.data.local.dto.DayEntity
import com.example.hydrateme.hydrateme.domain.model.Report
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import com.example.hydrateme.hydrateme.domain.use_case.util.getMilliFromDate
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit

class GetTodayReportUseCase(
    private val repository: HydrateRepository
) {

    suspend operator fun invoke(): Flow<Report>{
        return repository.getReport(1)
    }


}