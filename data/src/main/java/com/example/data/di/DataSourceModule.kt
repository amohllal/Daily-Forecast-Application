package com.example.data.di

import com.example.data.local.ForecastDAO
import com.example.data.remote.ForecastService
import com.example.data.source.ForecastLocalDataSource
import com.example.data.source.ForecastRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideForecastRemoteDataSource(
        forecastService: ForecastService,
    ): ForecastRemoteDataSource {
        return ForecastRemoteDataSource(forecastService)
    }

    @Provides
    @Singleton
    fun provideForecastLocalDataSource(
        forecastDAO: ForecastDAO
    ): ForecastLocalDataSource {
        return ForecastLocalDataSource(forecastDAO)
    }
}