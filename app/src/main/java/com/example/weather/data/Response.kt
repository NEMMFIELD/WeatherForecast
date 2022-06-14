package com.example.weather

import com.squareup.moshi.Json

data class Response(

	@Json(name="current")
	val current: Current? = null,

	@Json(name="location")
	val location: Location? = null
)

data class Current(

	@Json(name="feelslike_c")
	val feelslikeC: Double? = null,

	@Json(name="uv")
	val uv: Double? = null,

	@Json(name="last_updated")
	val lastUpdated: String? = null,

	@Json(name="feelslike_f")
	val feelslikeF: Double? = null,

	@Json(name="wind_degree")
	val windDegree: Int? = null,

	@Json(name="last_updated_epoch")
	val lastUpdatedEpoch: Int? = null,

	@Json(name="is_day")
	val isDay: Int? = null,

	@Json(name="precip_in")
	val precipIn: Double? = null,

	@Json(name="wind_dir")
	val windDir: String? = null,

	@Json(name="gust_mph")
	val gustMph: Double? = null,

	@Json(name="temp_c")
	val tempC: Double? = null,

	@Json(name="pressure_in")
	val pressureIn: Double? = null,

	@Json(name="gust_kph")
	val gustKph: Double? = null,

	@Json(name="temp_f")
	val tempF: Double? = null,

	@Json(name="precip_mm")
	val precipMm: Double? = null,

	@Json(name="cloud")
	val cloud: Int? = null,

	@Json(name="wind_kph")
	val windKph: Double? = null,

	@Json(name="condition")
	val condition: Condition? = null,

	@Json(name="wind_mph")
	val windMph: Double? = null,

	@Json(name="vis_km")
	val visKm: Double? = null,

	@Json(name="humidity")
	val humidity: Int? = null,

	@Json(name="pressure_mb")
	val pressureMb: Double? = null,

	@Json(name="vis_miles")
	val visMiles: Double? = null
)

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
)

data class Condition(

	@Json(name="code")
	val code: Int? = null,

	@Json(name="icon")
	val icon: String? = null,

	@Json(name="text")
	val text: String? = null
)
