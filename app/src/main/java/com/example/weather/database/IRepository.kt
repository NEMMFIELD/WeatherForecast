package com.example.weather.database

import android.content.Context
import com.example.weather.data.WeatherForecast
import com.example.weather.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface IRepository {
    suspend fun loadDataFromNetwork(city: String): WeatherForecast
   suspend fun insertDataToDb(city:String)
   suspend fun updateWeather(weatherEntity: WeatherEntity)
}

class Repository(context: Context) : IRepository {
    private val db: WeatherDataBase = WeatherDataBase.create(context)
    var currentWeather: Flow<WeatherEntity> = db.weatherDao().getAll()

    override suspend fun loadDataFromNetwork(city: String): WeatherForecast =
        withContext(Dispatchers.IO)
        {
            RetrofitHelper.retrofitService.getForecast(city)
        }

    override suspend fun insertDataToDb(city: String) {
        db.clearAllTables()
        val currentWeatherCity = convertModelToEntity(loadDataFromNetwork(city))
        db.weatherDao().insertWeather(currentWeatherCity)
    }

    override suspend fun updateWeather(weatherEntity: WeatherEntity) =
        db.weatherDao().updateWeather(weatherEntity)

    private fun convertModelToEntity(weatherForecast: WeatherForecast):WeatherEntity
    {
        return WeatherEntity(
            city = weatherForecast.location?.name.toString(),
           date = weatherForecast.location?.localtime.toString(),
            temp = weatherForecast.current?.tempC,
            info = weatherForecast.current?.condition?.text.toString(),
            maxTemp = weatherForecast.forecast?.forecastday?.get(0)?.day?.maxtempC,
            minTemp = weatherForecast.forecast?.forecastday?.get(0)?.day?.mintempC,
            icon = weatherForecast.current?.condition?.icon
        )
    }
}