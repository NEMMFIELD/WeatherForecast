package com.example.weather.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherModelHours(
    val date: String?,
    val info: String?,
    val forecastTemp: Double?,
    val forecastImg: String?
) : Parcelable

fun convertToWeatherHoursModel(
    generalClass: WeatherForecast?,
    countDays: Int,
    countHours: Int
): WeatherModelHours =
    WeatherModelHours(
        date = generalClass?.forecast?.forecastday?.get(countDays)?.hour?.get(countHours)?.time,
        info = generalClass?.forecast?.forecastday?.get(countDays)?.hour?.get(countHours)?.condition?.text,
        forecastTemp = generalClass?.forecast?.forecastday?.get(countDays)?.hour?.get(countHours)?.tempC,
        forecastImg = generalClass?.forecast?.forecastday?.get(countDays)?.hour?.get(countHours)?.condition?.icon,
    )