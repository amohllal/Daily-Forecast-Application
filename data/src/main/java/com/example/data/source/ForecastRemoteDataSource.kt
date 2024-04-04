package com.example.data.source

import com.example.data.BuildConfig
import com.example.data.remote.ForecastService
import javax.inject.Inject

class ForecastRemoteDataSource @Inject constructor(
    private val forecastService: ForecastService,
) {

    suspend fun getDailyForecast(lat: Double, long: Double) =
        forecastService.getDailyForecast(
            lat, long,
            BuildConfig.API_KEY
        )


}