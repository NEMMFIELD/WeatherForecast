package com.example.weather.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.weather.database.Repository
import com.example.weather.database.WeatherEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: Repository) : ViewModel() {
    var currentWeather: LiveData<WeatherEntity?> = repository.currentWeather.asLiveData()

    init {
        loadCity("Dubna")
    }

    fun loadCity(city: String) = viewModelScope.launch {
        try {
            repository.insertDataToDb(city)
        } catch (e: Exception) {
            Log.d("Error", e.toString())
        }
    }
}

class WeatherViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            WeatherViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}