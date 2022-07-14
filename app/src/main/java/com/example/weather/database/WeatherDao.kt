package com.example.weather.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("SELECT * from weather")
    fun getAll(): Flow<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weatherEntity: WeatherEntity)

    @Update
    fun updateWeather(weatherEntity: WeatherEntity)

    @Query("DELETE FROM weather")
    fun deleteAll()
}