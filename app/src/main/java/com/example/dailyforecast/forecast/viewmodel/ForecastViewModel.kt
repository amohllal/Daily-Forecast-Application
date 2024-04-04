package com.example.dailyforecast.forecast.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyforecast.util.ForecastViewState
import com.example.domain.usecase.GetDailyForecastUseCase
import com.example.domain.wrapper.DailyForecastResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val getDailyForecastUseCase: GetDailyForecastUseCase) :
    ViewModel() {

    private val _forecastViewState = MutableLiveData<ForecastViewState>()
    val forecastViewState: LiveData<ForecastViewState> = _forecastViewState


    fun fetchDailyForecast(lat: Double?, long: Double?, countyCode: String?) {
        viewModelScope.launch {
            getDailyForecastUseCase(lat ?: 0.0, long ?: 0.0, countyCode ?: "")
                .onStart {
                    _forecastViewState.value = ForecastViewState.Loading(true)
                }
                .onCompletion {
                    _forecastViewState.value = ForecastViewState.Loading(false)
                }.collect { result ->
                    when (result) {
                        is DailyForecastResult.Success -> {
                            _forecastViewState.value = ForecastViewState.Success(result.data)
                        }

                        is DailyForecastResult.LocalData -> {
                            _forecastViewState.value = ForecastViewState.Warning(result.data)
                        }

                        is DailyForecastResult.Error -> {
                            _forecastViewState.value =
                                ForecastViewState.Error("${result.errorMessage}")
                        }
                    }
                }
        }
    }
}