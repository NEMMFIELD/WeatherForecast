package com.example.weather.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey val city: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "temperature") val temp:Double?,
    @ColumnInfo(name = "description") val info:String,
    @ColumnInfo(name = "maxTemperature") val maxTemp:Double?,
    @ColumnInfo(name = "minTemperature") val minTemp:Double?,
    @ColumnInfo(name = "icon") val icon:String?
):Parcelable
