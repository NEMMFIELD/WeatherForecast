package com.example.weather.viewmodel


import android.util.Log
import androidx.lifecycle.*
import com.example.weather.data.WeatherForecast
import com.example.weather.network.WeatherApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//Shared ViewModel
@HiltViewModel
class ViewModelDays @Inject constructor(val weatherApi: WeatherApi) : ViewModel() {
    private val _forecastDays = MutableLiveData<WeatherForecast>()
    val forecastDays: LiveData<WeatherForecast> get() = _forecastDays
    init {
        viewModelScope.launch {
            try {
                _forecastDays.value = weatherApi.getForecast("Dubna")
            } catch (e: Exception) {
                Log.d("Error_start", e.toString())
            }
        }
    }

    fun setCity(name: String) {
        viewModelScope.launch {
            try {
                _forecastDays.value = weatherApi.getForecast(name)
            } catch (e: Exception) {
                Log.d("Error", e.toString())
            }

        }
    }
}



