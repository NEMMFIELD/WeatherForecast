package com.example.weather.di

import android.content.Context
import androidx.room.Room
import com.example.weather.database.IRepository
import com.example.weather.database.Repository
import com.example.weather.database.WeatherDao
import com.example.weather.database.WeatherDataBase
import com.example.weather.network.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context.applicationContext,
        WeatherDataBase::class.java,
        "weather_database"
    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideDao(dataBase: WeatherDataBase) = dataBase.weatherDao()

    @Singleton
    @Provides
    fun provideRepository(dao:WeatherDao, weatherApi: WeatherApi):IRepository = Repository(dao,weatherApi)
}