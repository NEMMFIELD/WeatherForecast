package com.example.weather.database

import android.content.Context
import androidx.room.Database
import androidx.room.*
@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
abstract class WeatherDataBase:RoomDatabase()
{
    abstract fun weatherDao():WeatherDao
}