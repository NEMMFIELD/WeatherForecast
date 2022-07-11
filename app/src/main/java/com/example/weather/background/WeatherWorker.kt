package com.example.weather.background

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.content.getSystemService
import androidx.lifecycle.asLiveData
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.weather.database.Repository
import com.example.weather.database.WeatherEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext


class WeatherWorker(private val context: Context, workerParams:WorkerParameters):
    CoroutineWorker(context,workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO)
    {
        return@withContext kotlin.runCatching {
          val repository = Repository(context = applicationContext)
            repository.insertDataToDb("Dubna")
            Log.d("WorkM","Work manager works")
            println("WORK MANAGER works")
           showNotification(repository.currentWeather.first())
            Result.success()
        }.getOrElse {
            Result.failure()
        }
    }
    private fun showNotification(currentCity:WeatherEntity)
    {
        val notification = WeatherNotification(context = context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification.createNotificationChannel()
        }
        notification.showNotification(currentCity)
    }
}
