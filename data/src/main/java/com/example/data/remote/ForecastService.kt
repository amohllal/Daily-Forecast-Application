package com.example.data.remote

import com.example.data.core.APP_ID
import com.example.data.core.FORECAST_END_POINT
import com.example.data.core.LAT
import com.example.data.core.LONG
import com.example.data.model.DailyForecastDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    @GET(FORECAST_END_POINT)
    suspend fun getDailyForecast(
        @Query(LAT) lat: Double,
        @Query(LONG) long: Double,
        @Query(APP_ID) appId: String
    ): Response<DailyForecastDTO>
}