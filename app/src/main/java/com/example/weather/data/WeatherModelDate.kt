package com.example.weather.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherModelDate(
    val date: String?,
    val info: String?,
    val forecastMaxTemp: Double?,
    val forecastMinTemp: Double?,
    val forecastImg: String?
) : Parcelable

fun convertToWeatherModel(generalClass: WeatherForecast?, count: Int): WeatherModelDate =
    WeatherModelDate(
        date = generalClass?.forecast?.forecastday?.get(count)?.date,
        info = generalClass?.forecast?.forecastday?.get(count)?.day?.condition?.text,
        forecastMaxTemp = generalClass?.forecast?.forecastday?.get(count)?.day?.maxtempC,
        forecastMinTemp = generalClass?.forecast?.forecastday?.get(count)?.day?.mintempC,
        forecastImg = generalClass?.forecast?.forecastday?.get(count)?.day?.condition?.icon
    )




