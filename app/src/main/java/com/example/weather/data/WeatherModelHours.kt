package com.example.weather.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.time.Duration.Companion.hours

@Parcelize
data class WeatherModelHours(
    val date: String?,
    val info: String?,
    val forecastTemp: Double?,
    val forecastImg: String?
) : Parcelable

fun convertToWeatherHoursModel(generalClass: WeatherForecast?, countHours:Int): WeatherModelHours =
    WeatherModelHours(
        date = generalClass?.forecast?.forecastday?.get(0)?.hour?.get(countHours)?.time,
        info = generalClass?.forecast?.forecastday?.get(0)?.hour?.get(countHours)?.condition?.text,
        forecastTemp = generalClass?.forecast?.forecastday?.get(0)?.hour?.get(countHours)?.tempC,
        forecastImg = generalClass?.forecast?.forecastday?.get(0)?.hour?.get(countHours)?.condition?.icon,
    )