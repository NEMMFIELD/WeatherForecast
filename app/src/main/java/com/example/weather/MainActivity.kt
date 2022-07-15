package com.example.weather

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.WorkManager
import com.example.weather.background.WeatherNotification
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.network.InternetConnection
import com.example.weather.ui.NetConnectionDialog
import com.example.weather.ui.NetConnectionDialog.Companion.TAG
import com.example.weather.ui.WeatherFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding
    private val checkConnection by lazy { InternetConnection(application)}
    val netDialog: NetConnectionDialog = NetConnectionDialog()
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, WeatherFragment::class.java, null).commit()
            }
        checkConnection.observe(this@MainActivity, Observer {
            if (!it)
            {
                netDialog.show(supportFragmentManager, TAG)
                WorkManager.getInstance(applicationContext).cancelAllWorkByTag("WM")
            }

        })
        Log.d("Lifecycle", "Main activity created")
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("Lifecycle", "New Intent is here")
    }

}
