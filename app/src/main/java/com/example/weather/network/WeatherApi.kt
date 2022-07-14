package com.example.weather.network

import com.example.weather.data.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
@GET("forecast.json?key=${Constants.API_KEY}&days=3&aqi=no&alerts=no")
suspend fun getForecast(@Query("q") city:String):WeatherForecast
}