package com.example.data.repository

import com.example.data.entity.DailyForecastEntity
import com.example.data.mapper.toDailyForecastEntityList
import com.example.data.mapper.toDailyForecastResponseList
import com.example.data.model.DailyForecastDTO
import com.example.data.source.ForecastLocalDataSource
import com.example.data.source.ForecastRemoteDataSource
import com.example.domain.repository.DailyForecastRepository
import com.example.domain.wrapper.DailyForecastResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DailyForecastRepositoryImpl @Inject constructor(
    private val remoteDataSource: ForecastRemoteDataSource,
    private val localDataSource: ForecastLocalDataSource
) : DailyForecastRepository {

    override suspend fun getDailyForecast(
        lat: Double,
        long: Double,
        countyCode: String
    ): Flow<DailyForecastResult> {
        return flow {
            val response = remoteDataSource.getDailyForecast(lat, long)
            if (response.isSuccessful) {
                val body = response.body()
                mapRemoteResponseToDomain(body)?.let { emit(DailyForecastResult.Success(it)) }
                mapRemoteResponseToLocalStorage(body)?.let {
                    localDataSource.saveDailyForecastForCity(
                        countyCode,
                        it
                    )
                }
            } else {
                val localData = localDataSource.getDailyForecast(countyCode)
                if (localData.isNotEmpty()) {
                    emit(DailyForecastResult.LocalData(mapLocalResponseToDomain(localData as ArrayList<DailyForecastEntity>)))
                } else {
                    emit(DailyForecastResult.Error("Something went wrong"))
                }
            }
        }
    }

    private fun mapRemoteResponseToDomain(dailyForecastDTO: DailyForecastDTO?) =
        dailyForecastDTO?.toDailyForecastResponseList()

    private fun mapRemoteResponseToLocalStorage(dailyForecastDTO: DailyForecastDTO?) =
        dailyForecastDTO?.toDailyForecastEntityList()


    private fun mapLocalResponseToDomain(forecastEntityList: ArrayList<DailyForecastEntity>) =
        forecastEntityList.toDailyForecastResponseList()


}