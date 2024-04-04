package com.example.domain.model

data class DailyForecastResponse(
    val temp: String?,
    val seaLevel: String?,
    val weatherDescription: String?,
    val weatherTitle: String?,
    val windSpeed: String?,
    val windDegree: String?
)
