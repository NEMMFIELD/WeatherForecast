package com.example.weather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.WeatherViewModel
import com.example.weather.WeatherViewModelFactory
import com.example.weather.data.WeatherForecastAdapter
import com.example.weather.data.WeatherModel
import com.example.weather.data.convertToWeatherModel

import com.example.weather.databinding.FragmentOneBinding


class FragmentOne : Fragment() {
    private val viewModel: WeatherViewModel by viewModels { WeatherViewModelFactory() }
    private val listWeather: MutableList<WeatherModel> = ArrayList()
    private lateinit var recyclerAdapter: WeatherForecastAdapter
    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.forecast.observe(this.viewLifecycleOwner)
        {
            for (i in it.forecast?.forecastday?.indices!!)
            {
                listWeather.add(convertToWeatherModel(it,i))
            }
            recyclerAdapter = WeatherForecastAdapter(listWeather)
            binding.apply {
                recycle.apply {
                    layoutManager = LinearLayoutManager(requireActivity())
                    adapter = recyclerAdapter
                }
            }
        }

    }
    companion object{
        fun newInstance():FragmentOne = FragmentOne()
    }

}