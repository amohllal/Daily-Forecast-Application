package com.example.dailyforecast.util

import com.example.domain.model.DailyForecastResponse

sealed class ForecastViewState {
    data class Loading(val isLoading: Boolean) : ForecastViewState()
    data class Error(val errorMessage: String) : ForecastViewState()
    data class Success(val data: List<DailyForecastResponse>) : ForecastViewState()
    data class Warning(val data: List<DailyForecastResponse>) : ForecastViewState()
}