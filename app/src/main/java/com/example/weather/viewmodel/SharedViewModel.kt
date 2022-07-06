package com.example.weather.viewmodel


import android.app.Application
import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.*
import androidx.work.WorkManager
import com.example.weather.background.WorkerRepository
import com.example.weather.data.WeatherForecast
import com.example.weather.network.RetrofitHelper
import kotlinx.coroutines.launch

//Shared ViewModel
class ViewModelDays() : ViewModel() {
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

class WeatherViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ViewModelDays::class.java)) {
            ViewModelDays() as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}


