package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.entity.DailyForecastEntity

@Dao
interface ForecastDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveDailyForecast(forecastEntityList: List<DailyForecastEntity>)

    @Query("SELECT * FROM daily_forecast WHERE country_code = :countyCode")
    fun getDailyForecastByCity(countyCode: String): List<DailyForecastEntity>


    @Query("DELETE FROM daily_forecast WHERE country_code = :countyCode")
    fun deleteDailyForecastByCity(countyCode: String)

    @Transaction
    fun saveDailyForecastForCity(cityName: String, forecastEntityList: List<DailyForecastEntity>) {
        deleteDailyForecastByCity(cityName)
        saveDailyForecast(forecastEntityList)
    }

}