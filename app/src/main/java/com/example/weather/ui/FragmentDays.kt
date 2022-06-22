package com.example.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.adapters.WeatherForecastAdapter
import com.example.weather.data.WeatherModelDate
import com.example.weather.data.convertToWeatherModel
import com.example.weather.databinding.FragmentOneBinding
import com.example.weather.viewmodel.ViewModelDays


class FragmentDays : Fragment() {
    private val sharedViewModel: ViewModelDays by activityViewModels()
    private val listWeather: MutableList<WeatherModelDate> = ArrayList()
    private var list: MutableList<WeatherModelDate> = ArrayList()
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
        sharedViewModel.forecastDays.observe(viewLifecycleOwner, Observer {
            //println(it.current?.tempC)
            for (i in it.forecast?.forecastday?.indices!!) {
                listWeather.add(convertToWeatherModel(it, i))
            }
            recyclerAdapter = WeatherForecastAdapter(listWeather)
            listWeather.clear()
            val newList = ArrayList<WeatherModelDate>()
            for (i in it.forecast.forecastday.indices) {
                newList.add(convertToWeatherModel(it, i))
            }
            listWeather.addAll(newList)
            recyclerAdapter.notifyDataSetChanged()
            binding.apply {
                recycle.apply {
                    layoutManager = LinearLayoutManager(requireActivity())
                    adapter = recyclerAdapter

                }
            }
        })
    }

    companion object{
        fun newInstance(): FragmentDays = FragmentDays()
    }
}