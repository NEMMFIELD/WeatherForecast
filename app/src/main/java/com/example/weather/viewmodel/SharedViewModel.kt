package com.example.weather.viewmodel


import android.util.Log
import androidx.lifecycle.*
import com.example.weather.data.WeatherForecast
import com.example.weather.network.RetrofitHelper
import kotlinx.coroutines.launch

//Shared ViewModel
class ViewModelDays : ViewModel() {
    private val _forecastDays = MutableLiveData<WeatherForecast>()
    val forecastDays: LiveData<WeatherForecast> get() = _forecastDays

    init {
        viewModelScope.launch {
            try {
                _forecastDays.value = RetrofitHelper.retrofitService.getForecast("Dubna")
            } catch (e: Exception) {
                Log.d("Error_start", e.toString())
            }
        }
    }

    fun setCity(name: String) {
        viewModelScope.launch {
            try {
                _forecastDays.value = RetrofitHelper.retrofitService.getForecast(name)
            } catch (e: Exception) {
                Log.d("Error", e.toString())
            }

        }
    }
}

class ViewModelDaysFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        ViewModelDays::class.java -> ViewModelDays()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}
