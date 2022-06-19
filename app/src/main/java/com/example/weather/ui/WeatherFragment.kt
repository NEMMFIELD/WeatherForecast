package com.example.weather.ui

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.weather.WeatherViewModel
import com.example.weather.WeatherViewModelFactory
import com.example.weather.data.VPAdapter
import com.example.weather.data.WeatherForecastAdapter
import com.example.weather.data.WeatherModel
import com.example.weather.data.convertToWeatherModel
import com.example.weather.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherViewModel by viewModels { WeatherViewModelFactory() }
    private lateinit var viewPager: ViewPager2
    private val listWeather:MutableList<WeatherModel> = ArrayList()
    private lateinit var recyclerAdapter:WeatherForecastAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        if (savedInstanceState != null) {
            binding.cityId.hint = ""
            binding.cityName.text = ""
            binding.weatherInfo.text = ""
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cityId.gravity = Gravity.CENTER_HORIZONTAL
        binding.buttonId.setOnClickListener {
            viewModel.getCurrentWeather(binding.cityId.text.toString())
        }

        viewModel.forecast.observe(this.viewLifecycleOwner)
        {
            with(binding)
            {
                tempId.text = it.current?.tempC.toString().plus("°")
                cityName.text = it.location?.name
                weatherIcon.load("https:${it.current?.condition?.icon}")
                weatherDate.text = it.location?.localtime
                weatherInfo.text = it.current?.condition?.text
                tvMaxMin.text = "${it.forecast?.forecastday?.get(0)?.day?.maxtempC}°/${it.forecast?.forecastday?.get(0)?.day?.mintempC}°"
            }
            listWeather.addAll(convertToWeatherModel(it))
            println("The Result is:$listWeather")
           //binding.vp.adapter = VPAdapter(listWeather)
            recyclerAdapter = WeatherForecastAdapter(listWeather)
            binding.apply {
                recycle.apply {
                    layoutManager = LinearLayoutManager(requireActivity())
                    adapter=recyclerAdapter
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("editText", binding.cityId.text.toString())
    }
}