package com.example.data.di


import com.example.data.repository.DailyForecastRepositoryImpl
import com.example.domain.repository.DailyForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideDailyForecastRepository(dailyForecastRepositoryImpl: DailyForecastRepositoryImpl): DailyForecastRepository


}