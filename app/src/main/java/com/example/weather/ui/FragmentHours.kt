package com.example.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.adapters.WeatherForecastAdapterHours
import com.example.weather.data.WeatherModelHours
import com.example.weather.data.convertToWeatherHoursModel
import com.example.weather.databinding.FragmentTwoBinding
import com.example.weather.viewmodel.ViewModelDays

class FragmentHours : Fragment() {
    private var _binding: FragmentTwoBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: ViewModelDays by activityViewModels()
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
        sharedViewModel.forecastDays.observe(this.viewLifecycleOwner, Observer {
            for (i in it.forecast?.forecastday?.indices!!) {
                for (j in it.forecast.forecastday[i]?.hour?.indices!!) {
                    listWeather.add(convertToWeatherHoursModel(it, i, j))
                }
            }

            recyclerAdapterHours = WeatherForecastAdapterHours(listWeather)
            listWeather.clear()
            val newList = ArrayList<WeatherModelHours>()
            for (i in it.forecast.forecastday.indices) {
                for (j in it.forecast.forecastday[i]?.hour?.indices!!) {
                    newList.add(convertToWeatherHoursModel(it, i, j))
                }
            }

            listWeather.addAll(newList)
            // println("size is:${listWeather.size}")
            for (i in listWeather.indices) {
                println(listWeather[i])
            }

            recyclerAdapterHours.notifyDataSetChanged()
            binding.apply {
                recycleHours.apply {
                    layoutManager = LinearLayoutManager(requireActivity())
                    adapter = recyclerAdapterHours
                    recyclerAdapterHours.notifyDataSetChanged()
                }
            }

            println("Adapter: ${recyclerAdapterHours.items}")
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(): FragmentHours = FragmentHours()
    }
}
