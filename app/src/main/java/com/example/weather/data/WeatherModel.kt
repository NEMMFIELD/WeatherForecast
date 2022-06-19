package com.example.weather.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherModel(
    val date: String?,
    val info: String?,
    val forecastMaxTemp: Double?,
    val forecastMinTemp: Double?,
    val forecastImg: String?
) : Parcelable

    fun convertToWeatherModel(generalClass: WeatherForecast?): List<WeatherModel> =
        listOf(
            WeatherModel(
                date = generalClass?.forecast?.forecastday?.get(0)?.date,
                info = generalClass?.forecast?.forecastday?.get(0)?.day?.condition?.text,
                forecastMaxTemp = generalClass?.forecast?.forecastday?.get(0)?.day?.maxtempC,
                forecastMinTemp = generalClass?.forecast?.forecastday?.get(0)?.day?.mintempC,
                forecastImg = generalClass?.forecast?.forecastday?.get(0)?.day?.condition?.icon
            ),
            WeatherModel(
                date = generalClass?.forecast?.forecastday?.get(1)?.date,
                info = generalClass?.forecast?.forecastday?.get(1)?.day?.condition?.text,
                forecastMaxTemp = generalClass?.forecast?.forecastday?.get(1)?.day?.maxtempC,
                forecastMinTemp = generalClass?.forecast?.forecastday?.get(1)?.day?.mintempC,
                forecastImg = generalClass?.forecast?.forecastday?.get(1)?.day?.condition?.icon
            )
        )

