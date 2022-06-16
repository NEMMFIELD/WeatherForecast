package com.example.weather.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.weather.WeatherViewModel
import com.example.weather.WeatherViewModelFactory
import com.example.weather.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherViewModel by viewModels { WeatherViewModelFactory() }

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
                tempId.text = it.current?.tempC.toString().plus(" °C")
                cityName.text = it.location?.name
                weatherIcon.load("https:${it.current?.condition?.icon}")
                weatherDate.text = it.location?.localtime
                weatherInfo.text = it.current?.condition?.text
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