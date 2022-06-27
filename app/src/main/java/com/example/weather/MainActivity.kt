package com.example.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.network.InternetConnection
import com.example.weather.ui.NetConnectionDialog
import com.example.weather.ui.NetConnectionDialog.Companion.TAG
import com.example.weather.ui.WeatherFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val checkConnection: InternetConnection = InternetConnection()
        val netDialog: NetConnectionDialog = NetConnectionDialog()
        if (!checkConnection.isOnline(this)) netDialog.show(supportFragmentManager, TAG)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, WeatherFragment::class.java, null).commit()
        }
    }
}
