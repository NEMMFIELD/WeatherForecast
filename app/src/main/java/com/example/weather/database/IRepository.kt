package com.example.weather.database

import com.example.weather.data.WeatherForecast
import com.example.weather.network.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface IRepository {
    suspend fun loadDataFromNetwork(city: String): WeatherForecast
   suspend fun insertDataToDb(city:String)
   suspend fun updateWeather(weatherEntity: WeatherEntity)
}


class Repository @Inject constructor(private val dao:WeatherDao, private val weatherApi: WeatherApi) : IRepository {

    var currentWeather: Flow<WeatherEntity> = dao.getAll()

    override suspend fun loadDataFromNetwork(city: String): WeatherForecast =
        withContext(Dispatchers.IO)
        {
         weatherApi.getForecast(city)
        }

    override suspend fun insertDataToDb(city: String) {
        dao.deleteAll()
        val currentWeatherCity = convertModelToEntity(loadDataFromNetwork(city))
        dao.insertWeather(currentWeatherCity)
    }

    override suspend fun updateWeather(weatherEntity: WeatherEntity) =
      dao.updateWeather(weatherEntity)

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