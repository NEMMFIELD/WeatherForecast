package com.example.weather.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.weather.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class WeatherWorker(appContext: Context, workerParams:WorkerParameters):
    CoroutineWorker(appContext,workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO)
    {
        return@withContext kotlin.runCatching {
            val request = RetrofitHelper.retrofitService.getForecast("Moscow")
            println("WORK MANAGER works")
            Result.success()
        }.getOrElse {
            Result.failure()
        }
    }
}
