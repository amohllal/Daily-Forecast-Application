package com.example.domain.repository

import com.example.domain.wrapper.DailyForecastResult
import kotlinx.coroutines.flow.Flow

interface DailyForecastRepository {
    suspend fun getDailyForecast(
        lat: Double,
        long: Double,
        countyCode: String
    ): Flow<DailyForecastResult>
}