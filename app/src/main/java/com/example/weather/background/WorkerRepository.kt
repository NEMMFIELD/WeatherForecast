package com.example.weather.background

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import com.example.weather.network.WORKER_DELAY_TIME
import java.util.concurrent.TimeUnit

class WorkerRepository {
    private val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
    val periodicWork = PeriodicWorkRequest.Builder(
        WeatherWorker::class.java,
        15L,
        TimeUnit.MINUTES
    ).setConstraints(constraints)
        .addTag("WM")
        .setInitialDelay(WORKER_DELAY_TIME, TimeUnit.SECONDS)
        .build()
}