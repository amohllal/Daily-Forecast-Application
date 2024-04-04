package com.example.data.di

import android.app.Application
import com.example.data.BuildConfig
import com.example.data.core.NetworkConnectionInterceptor
import com.example.data.remote.ForecastService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(networkConnectionInterceptor)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.MINUTES)
            .build()


    @Provides
    fun provideForecastService(okHttpClient: OkHttpClient): ForecastService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ForecastService::class.java)


    @Provides
    fun provideNetworkConnectionInterceptor(appContext: Application): NetworkConnectionInterceptor =
        NetworkConnectionInterceptor(appContext)

    @Provides
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}