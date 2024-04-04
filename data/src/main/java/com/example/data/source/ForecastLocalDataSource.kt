package com.example.data.source

import com.example.data.entity.DailyForecastEntity
import com.example.data.local.ForecastDAO
import javax.inject.Inject

class ForecastLocalDataSource @Inject constructor(
    private val forecastDAO: ForecastDAO
) {

    fun getDailyForecast(countyCode: String): List<DailyForecastEntity> {
        return forecastDAO.getDailyForecastByCity(countyCode)
    }

    fun saveDailyForecastForCity(countyCode: String, forecastEntityList: List<DailyForecastEntity>) {
        forecastDAO.saveDailyForecastForCity(countyCode, forecastEntityList)
    }

}