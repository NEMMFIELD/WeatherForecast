package com.example.weather.background

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.impl.model.Dependency
import com.example.weather.database.Repository
import com.example.weather.database.WeatherEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

@HiltWorker
class WeatherWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: Repository
) :
    CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO)
    {
        return@withContext kotlin.runCatching {
            repository.insertDataToDb("Dubna")
            Log.d("WorkM", "Work manager works")
            println("WORK MANAGER works")
            showNotification(repository.currentWeather.first())
            Result.success()
        }.getOrElse {
            Result.failure()
        }
    }

    private fun showNotification(currentCity: WeatherEntity) {
        val notification = WeatherNotification(context = context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification.createNotificationChannel()
        }
        notification.showNotification(currentCity)
    }
}
