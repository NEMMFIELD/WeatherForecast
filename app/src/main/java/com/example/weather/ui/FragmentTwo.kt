package com.example.weather.ui

import android.os.Bundle
import android.util.ArraySet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.WeatherViewModel
import com.example.weather.WeatherViewModelFactory
import com.example.weather.data.*
import com.example.weather.databinding.FragmentTwoBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class FragmentTwo : Fragment() {
    private var _binding: FragmentTwoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherViewModel by viewModels { WeatherViewModelFactory() }
    private val listWeather: MutableList<WeatherModelHours> = ArrayList()
    private lateinit var recyclerAdapterHours: WeatherForecastAdapterHours
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.forecast.observe(this.viewLifecycleOwner)
        {
                for (j in it.forecast?.forecastday?.get(0)?.hour?.indices!!)
                {
                    listWeather.add(convertToWeatherHoursModel(it,j))
                }
            recyclerAdapterHours = WeatherForecastAdapterHours(listWeather)
            recyclerAdapterHours.submitList(listWeather)
            binding.apply {
                recycleHours.apply {
                    layoutManager = LinearLayoutManager(requireActivity())
                    adapter = recyclerAdapterHours
                }
            }

        }

    }

    companion object {
        @JvmStatic
        fun newInstance():FragmentTwo = FragmentTwo()
    }
}
