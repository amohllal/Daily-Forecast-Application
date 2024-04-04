package com.example.domain.usecase

import com.example.domain.model.DailyForecastResponse
import com.example.domain.repository.DailyForecastRepository
import com.example.domain.wrapper.DailyForecastResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetDailyForecastUseCaseTest {


    private lateinit var dailyForecastRepository: DailyForecastRepository
    private lateinit var dailyForecastUseCase: GetDailyForecastUseCase

    @Before
    fun setup() {
        dailyForecastRepository = mock(DailyForecastRepository::class.java)
        dailyForecastUseCase = GetDailyForecastUseCase(dailyForecastRepository)
    }

    private fun mockDailyForecastResponse(): DailyForecastResponse {
        return DailyForecastResponse(
            temp = "25°C",
            seaLevel = "1024 hPa",
            weatherDescription = "Partly cloudy",
            weatherTitle = "Clouds",
            windSpeed = "5 m/s",
            windDegree = "180°"
        )
    }


    @Test
    fun `invoke daily forecast use case with successful response`(): Unit = runBlocking {

        val mockResult = DailyForecastResult.Success(arrayListOf(mockDailyForecastResponse()))
        `when`(dailyForecastRepository.getDailyForecast(23.233, 32.345, "EG")).thenReturn(flow {
            emit(mockResult)
        })

        val result = dailyForecastUseCase.invoke(23.233, 32.345, "EG")

        assertEquals(mockResult, result.single())
    }


    @Test
    fun `invoke daily forecast use case with local data available`(): Unit = runBlocking {

        val mockLocalDataResult =
            DailyForecastResult.LocalData(arrayListOf(mockDailyForecastResponse()))
        `when`(dailyForecastRepository.getDailyForecast(23.233, 32.345, "EG")).thenReturn(flow {
            emit(mockLocalDataResult)
        })

        val result = dailyForecastUseCase.invoke(23.233, 32.345, "EG")

        assertEquals(mockLocalDataResult, result.single())
    }

    @Test
    fun `invoke daily forecast use case with error`(): Unit = runBlocking {
        val mockErrorResult = DailyForecastResult.Error("Something went wrong")
        `when`(dailyForecastRepository.getDailyForecast(23.233, 32.345, "EG")).thenReturn(flow {
            emit(mockErrorResult)
        })

        val result = dailyForecastUseCase.invoke(23.233, 32.345, "EG")

        assertEquals(mockErrorResult, result.single())
    }
}