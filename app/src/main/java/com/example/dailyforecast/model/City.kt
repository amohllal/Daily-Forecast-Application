package com.example.dailyforecast.model

data class City(
    val id: Int?= null,
    val cityNameAr: String?= null,
    val cityNameEn: String?= null,
    val country: String?= null,
    val lat: Double?= null,
    val lon: Double?= null
)
