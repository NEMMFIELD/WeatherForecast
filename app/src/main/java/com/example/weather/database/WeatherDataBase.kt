package com.example.weather.database

import android.content.Context
import androidx.room.Database
import androidx.room.*
@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
abstract class WeatherDataBase:RoomDatabase()
{
    abstract fun weatherDao():WeatherDao
    companion object{
        @Volatile
        private var INSTANCE:WeatherDataBase? = null
        fun create(context: Context):WeatherDataBase{
            return INSTANCE?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDataBase::class.java,
                    "weather_database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}