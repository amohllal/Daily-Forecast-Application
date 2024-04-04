package com.example.domain.usecase

import com.example.domain.repository.DailyForecastRepository
import javax.inject.Inject

class GetDailyForecastUseCase @Inject constructor(private val dailyForecastRepository: DailyForecastRepository) {
    suspend operator fun invoke(lat: Double, long: Double, countyCode: String) =
        dailyForecastRepository.getDailyForecast(lat, long, countyCode)
}