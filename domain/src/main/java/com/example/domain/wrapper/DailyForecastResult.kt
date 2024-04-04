package com.example.domain.wrapper

import com.example.domain.model.DailyForecastResponse

sealed class DailyForecastResult {
    data class Success(val data: ArrayList<DailyForecastResponse>) : DailyForecastResult()
    data class LocalData(val data: ArrayList<DailyForecastResponse>) : DailyForecastResult()
    data class Error(val errorMessage: String) : DailyForecastResult()

}