package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entity.DailyForecastEntity

@Database(
    entities = [DailyForecastEntity::class],
    version = 13,
    exportSchema = false
)
abstract class ForecastDataBase : RoomDatabase() {
    abstract fun getForecastDao(): ForecastDAO

}