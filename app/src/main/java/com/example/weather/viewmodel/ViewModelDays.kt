package com.example.weather.viewmodel


import androidx.lifecycle.*
import com.example.weather.data.WeatherForecast
import com.example.weather.network.RetrofitHelper
import kotlinx.coroutines.launch

//Shared ViewModel
class ViewModelDays : ViewModel() {
    private val _forecastDays = MutableLiveData<WeatherForecast>()
    val forecastDays: LiveData<WeatherForecast> get() = _forecastDays

    fun setCity(name: String) {
        viewModelScope.launch {
            _forecastDays.value = RetrofitHelper.retrofitService.getForecast(name)
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
