package com.example.weather.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.weather.database.Repository
import com.example.weather.database.WeatherEntity
import dagger.Module
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class WeatherViewModel
@Inject constructor(private val repository: Repository) : ViewModel() {
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
