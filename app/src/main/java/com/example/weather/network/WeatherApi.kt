package com.example.weather.network

import com.example.weather.Response
import com.example.weather.data.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
//@GET("current.json?key=${API_KEY}&aqi=no")
//suspend fun getWeather(@Query("q")city:String): Response

@GET("forecast.json?key=${API_KEY}&days=2&aqi=no&alerts=no")
suspend fun getForecast(@Query("q") city:String):WeatherForecast
}