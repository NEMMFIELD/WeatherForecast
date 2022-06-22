package com.example.weather

import android.util.Log
import androidx.lifecycle.*
import com.example.weather.data.WeatherForecast
import com.example.weather.network.RetrofitHelper
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _forecast = MutableLiveData<WeatherForecast>()
    val forecast:LiveData<WeatherForecast> get () = _forecast

    init {
        viewModelScope.launch { _forecast.value = RetrofitHelper.retrofitService.getForecast("Dubna") }
    }
    fun getCurrentWeather(name:String)
    {
        viewModelScope.launch {
            try {
                _forecast.value = RetrofitHelper.retrofitService.getForecast(name)
            } catch (e: Exception) {
                Log.d("Error", "Something went wrong")
            }
        }
    }
}


class WeatherViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        WeatherViewModel::class.java -> WeatherViewModel()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}

