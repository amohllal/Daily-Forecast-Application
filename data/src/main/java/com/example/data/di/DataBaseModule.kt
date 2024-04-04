package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.ForecastDAO
import com.example.data.local.ForecastDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    fun provideForecastDao(appDatabase: ForecastDataBase): ForecastDAO {
        return appDatabase.getForecastDao()
    }

    @Provides
    @Singleton
    fun provideForecastDatabase(@ApplicationContext appContext: Context)
            : ForecastDataBase {
        return Room.databaseBuilder(
            appContext,
            ForecastDataBase::class.java,
            "forecast_Database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}