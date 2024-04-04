package com.example.data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_forecast")
data class DailyForecastEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "temp")
    val temp: String?,

    @ColumnInfo(name = "sea_level")
    val seaLevel: String?,

    @ColumnInfo(name = "weather_description")
    val weatherDescription: String?,

    @ColumnInfo(name = "weather_title")
    val weatherTitle: String?,

    @ColumnInfo(name = "wind_speed")
    val windSpeed: String?,

    @ColumnInfo(name = "wind_degree")
    val windDegree: String?,

    @Embedded
    val cityDetails: CityDetails
)

data class CityDetails(
    @ColumnInfo(name = "city_name")
    val name: String?,

    @ColumnInfo(name = "country_code")
    val countryCode: String?,

    @ColumnInfo(name = "sunset_time")
    val sunSit: String?,

    @ColumnInfo(name = "sunrise_time")
    val sunRise: String?
)