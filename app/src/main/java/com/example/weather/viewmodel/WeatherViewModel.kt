package com.example.weather

import android.util.Log
import androidx.lifecycle.*
import com.example.weather.network.RetrofitHelper
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _temp = MutableLiveData<String>()
    val temp: LiveData<String> get() = _temp
    private val _city = MutableLiveData<String>()
    val city: LiveData<String> get() = _city
    private val _icon = MutableLiveData<String>()
    val icon: LiveData<String> get() = _icon
   /* fun getTemperature(name: String) {
        viewModelScope.launch {
            try {
                _temp.value =
                    RetrofitHelper.retrofitService.getWeather(name).current?.tempC.toString()
                        .plus(" °C")
            } catch (e: Exception) {
                Log.d("Error", "Something went wrong")
            }
        }
    }*/

    fun getCurrentWeather(name:String)
    {
        viewModelScope.launch {
            try {
                _temp.value =
                    RetrofitHelper.retrofitService.getForecast(name).current?.tempC.toString()
                        .plus(" °C")
                _city.value = RetrofitHelper.retrofitService.getForecast(name).location?.name.toString()
                _icon.value = RetrofitHelper.retrofitService.getForecast(name).current?.condition?.icon.toString()
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

