package com.example.weather.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherForecast(

	@Json(name="current")
	val current: Current? = null,

	@Json(name="location")
	val location: Location? = null,

	@Json(name="forecast")
	val forecast: Forecast? = null
) : Parcelable

@Parcelize
data class Current(

	@Json(name="temp_c")
	val tempC: Double? = null,

	@Json(name="condition")
	val condition: Condition? = null
) : Parcelable

@Parcelize
data class Astro(

	@Json(name="moonset")
	val moonset: String? = null,

	@Json(name="moon_illumination")
	val moonIllumination: String? = null,

	@Json(name="sunrise")
	val sunrise: String? = null,

	@Json(name="moon_phase")
	val moonPhase: String? = null,

	@Json(name="sunset")
	val sunset: String? = null,

	@Json(name="moonrise")
	val moonrise: String? = null
) : Parcelable

@Parcelize
data class ForecastdayItem(

	@Json(name="date")
	val date: String? = null,

	@Json(name="astro")
	val astro: Astro? = null,

	@Json(name="date_epoch")
	val dateEpoch: Int? = null,

	@Json(name="hour")
	val hour: List<HourItem?>? = null,

	@Json(name="day")
	val day: Day? = null
) : Parcelable

@Parcelize
data class Condition(

	@Json(name="icon")
	val icon: String? = null,

	@Json(name="text")
	val text: String? = null
) : Parcelable

@Parcelize
data class HourItem(

	@Json(name="feelslike_c")
	val feelslikeC: Double? = null,

	@Json(name="feelslike_f")
	val feelslikeF: Double? = null,

	@Json(name="wind_degree")
	val windDegree: Int? = null,

	@Json(name="windchill_f")
	val windchillF: Double? = null,

	@Json(name="windchill_c")
	val windchillC: Double? = null,

	@Json(name="temp_c")
	val tempC: Double? = null,

	@Json(name="temp_f")
	val tempF: Double? = null,

	@Json(name="cloud")
	val cloud: Int? = null,

	@Json(name="wind_kph")
	val windKph: Double? = null,

	@Json(name="wind_mph")
	val windMph: Double? = null,

	@Json(name="humidity")
	val humidity: Int? = null,

	@Json(name="dewpoint_f")
	val dewpointF: Double? = null,

	@Json(name="will_it_rain")
	val willItRain: Int? = null,

	@Json(name="uv")
	val uv: Double? = null,

	@Json(name="heatindex_f")
	val heatindexF: Double? = null,

	@Json(name="dewpoint_c")
	val dewpointC: Double? = null,

	@Json(name="is_day")
	val isDay: Int? = null,

	@Json(name="precip_in")
	val precipIn: Double? = null,

	@Json(name="heatindex_c")
	val heatindexC: Double? = null,

	@Json(name="wind_dir")
	val windDir: String? = null,

	@Json(name="gust_mph")
	val gustMph: Double? = null,

	@Json(name="pressure_in")
	val pressureIn: Double? = null,

	@Json(name="chance_of_rain")
	val chanceOfRain: Int? = null,

	@Json(name="gust_kph")
	val gustKph: Double? = null,

	@Json(name="precip_mm")
	val precipMm: Double? = null,

	@Json(name="condition")
	val condition: Condition? = null,

	@Json(name="will_it_snow")
	val willItSnow: Int? = null,

	@Json(name="vis_km")
	val visKm: Double? = null,

	@Json(name="time_epoch")
	val timeEpoch: Int? = null,

	@Json(name="time")
	val time: String? = null,

	@Json(name="chance_of_snow")
	val chanceOfSnow: Int? = null,

	@Json(name="pressure_mb")
	val pressureMb: Double? = null,

	@Json(name="vis_miles")
	val visMiles: Double? = null
) : Parcelable

@Parcelize
data class Location(

	@Json(name="localtime")
	val localtime: String? = null,

	@Json(name="country")
	val country: String? = null,

	@Json(name="localtime_epoch")
	val localtimeEpoch: Int? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="lon")
	val lon: Double? = null,

	@Json(name="region")
	val region: String? = null,

	@Json(name="lat")
	val lat: Double? = null,

	@Json(name="tz_id")
	val tzId: String? = null
) : Parcelable

@Parcelize
data class Forecast(

	@Json(name="forecastday")
	val forecastday: List<ForecastdayItem?>? = null
) : Parcelable

@Parcelize
data class Day(

	@Json(name="avgvis_km")
	val avgvisKm: Double? = null,

	@Json(name="uv")
	val uv: Double? = null,

	@Json(name="avgtemp_f")
	val avgtempF: Double? = null,

	@Json(name="avgtemp_c")
	val avgtempC: Double? = null,

	@Json(name="daily_chance_of_snow")
	val dailyChanceOfSnow: Int? = null,

	@Json(name="maxtemp_c")
	val maxtempC: Double? = null,

	@Json(name="maxtemp_f")
	val maxtempF: Double? = null,

	@Json(name="mintemp_c")
	val mintempC: Double? = null,

	@Json(name="avgvis_miles")
	val avgvisMiles: Double? = null,

	@Json(name="daily_will_it_rain")
	val dailyWillItRain: Int? = null,

	@Json(name="mintemp_f")
	val mintempF: Double? = null,

	@Json(name="totalprecip_in")
	val totalprecipIn: Double? = null,

	@Json(name="avghumidity")
	val avghumidity: Double? = null,

	@Json(name="condition")
	val condition: Condition? = null,

	@Json(name="maxwind_kph")
	val maxwindKph: Double? = null,

	@Json(name="maxwind_mph")
	val maxwindMph: Double? = null,

	@Json(name="daily_chance_of_rain")
	val dailyChanceOfRain: Int? = null,

	@Json(name="totalprecip_mm")
	val totalprecipMm: Double? = null,

	@Json(name="daily_will_it_snow")
	val dailyWillItSnow: Int? = null
) : Parcelable
