package com.example.weather

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import com.example.weather.background.WeatherWorker
import com.example.weather.network.Constants
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class WeatherApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory
    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val periodicWork = PeriodicWorkRequest.Builder(
            WeatherWorker::class.java,
            15L,
            TimeUnit.MINUTES
        ).setConstraints(constraints)
            .addTag("WM")
            .setInitialDelay(Constants.WORKER_DELAY_TIME, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "WeatherUpdate",
            ExistingPeriodicWorkPolicy.KEEP, periodicWork
        )
    }
}