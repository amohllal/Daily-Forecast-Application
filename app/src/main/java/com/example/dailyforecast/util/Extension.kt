package com.example.dailyforecast.util

import android.content.Context
import com.example.dailyforecast.model.City
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

fun String.loadJSONFromAsset(context: Context): String? {
    return try {
        val inputStream = context.assets.open(this)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charsets.UTF_8)
    } catch (ex: IOException) {
        ex.printStackTrace()
        null
    }
}

fun String.parseJSON(): List<City> {
    val cities = mutableListOf<City>()
    try {
        val jsonObject = JSONObject(this)
        val citiesArray = jsonObject.getJSONArray("cities")
        for (i in 0 until citiesArray.length()) {
            val cityObject = citiesArray.getJSONObject(i)
            val city = City(
                id = cityObject.getInt("id"),
                cityNameAr = cityObject.getString("cityNameAr"),
                cityNameEn = cityObject.getString("cityNameEn"),
                country = cityObject.getString("country"),
                lat = cityObject.getDouble("lat"),
                lon = cityObject.getDouble("lon")
            )
            cities.add(city)
        }
    } catch (ex: JSONException) {
        ex.printStackTrace()
    }
    return cities
}