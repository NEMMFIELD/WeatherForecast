package com.example.weather.database

import android.content.Context
import com.example.weather.data.WeatherForecast
import com.example.weather.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface IRepository {
    suspend fun loadDataFromNetwork(city: String): WeatherForecast
   // suspend fun getWeather():List<WeatherEntity>
   suspend fun insertDataToDb(city:String)
}

class Repository(context: Context) : IRepository {
    private val db: WeatherDataBase = WeatherDataBase.create(context)
    val currentWeather: Flow<List<WeatherEntity>> = db.weatherDao().getAll()

    override suspend fun loadDataFromNetwork(city: String): WeatherForecast =
        withContext(Dispatchers.IO)
        {
            RetrofitHelper.retrofitService.getForecast(city)
        }

  //  override suspend fun getWeather() = db.weatherDao().getAll()

    override suspend fun insertDataToDb(city: String) {
        val currentWeatherCity = convertModelToEntity(loadDataFromNetwork(city))
        db.weatherDao().insertWeather(currentWeatherCity)
    }

    fun convertModelToEntity(weatherForecast: WeatherForecast):WeatherEntity
    {
        return WeatherEntity(
            city = weatherForecast.location?.name.toString(),
           date = weatherForecast.location?.localtime.toString(),
            temp = weatherForecast.current?.tempC,
            info = weatherForecast.current?.condition.toString(),
            maxTemp = weatherForecast.forecast?.forecastday?.get(0)?.day?.maxtempC,
            minTemp = weatherForecast.forecast?.forecastday?.get(0)?.day?.mintempC,
            icon = weatherForecast.current?.condition?.icon
        )
    }

}