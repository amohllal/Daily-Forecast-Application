package com.example.data.mapper

import com.example.data.entity.DailyForecastEntity
import com.example.data.model.DailyForecastDTO
import com.example.domain.model.DailyForecastResponse

fun DailyForecastDTO.toDailyForecastResponseList(): ArrayList<DailyForecastResponse> {
    return this.weatherList?.map { forecast ->
        DailyForecastResponse(
            temp = forecast.main?.temp.toString(),
            seaLevel = forecast.main?.seaLevel.toString(),
            weatherDescription = forecast.weather?.firstOrNull()?.description,
            weatherTitle = forecast.weather?.firstOrNull()?.main,
            windSpeed = forecast.wind?.speed.toString(),
            windDegree = forecast.wind?.deg.toString()
        )
    } as ArrayList<DailyForecastResponse>
}

fun DailyForecastDTO.toDailyForecastEntityList(): ArrayList<DailyForecastEntity> {
    return this.weatherList?.map { forecast ->
        DailyForecastEntity(
            temp = forecast.main?.temp.toString(),
            seaLevel = forecast.main?.seaLevel.toString(),
            weatherDescription = forecast.weather?.firstOrNull()?.description,
            weatherTitle = forecast.weather?.firstOrNull()?.main,
            windSpeed = forecast.wind?.speed.toString(),
            windDegree = forecast.wind?.deg.toString(),
            cityDetails = com.example.data.entity.CityDetails(
                name = this.city?.name,
                sunSit = this.city?.sunset.toString(),
                sunRise = this.city?.sunrise.toString(),
                countryCode = this.city?.country.toString()
            )
        )
    } as ArrayList<DailyForecastEntity>
}


fun ArrayList<DailyForecastEntity>.toDailyForecastResponseList(): ArrayList<DailyForecastResponse> {
    return map { entity ->
        DailyForecastResponse(
            temp = entity.temp,
            seaLevel = entity.seaLevel,
            weatherDescription = entity.weatherDescription,
            weatherTitle = entity.weatherTitle,
            windSpeed = entity.windSpeed,
            windDegree = entity.windDegree
        )
    } as ArrayList<DailyForecastResponse>
}
