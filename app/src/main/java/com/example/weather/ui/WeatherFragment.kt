package com.example.weather.ui

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import coil.load
import com.example.weather.adapters.VPAdapter
import com.example.weather.background.WorkerRepository
import com.example.weather.database.Repository
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.viewmodel.ViewModelDays
import com.example.weather.viewmodel.WeatherViewModel
import com.example.weather.viewmodel.WeatherViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import java.security.Policy

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: ViewModelDays by activityViewModels()
    private val viewModel: WeatherViewModel by viewModels {
        WeatherViewModelFactory(
            Repository(
                requireContext()
            )
        )
    }
    private var fragList = mutableListOf(FragmentDays.newInstance(), FragmentHours.newInstance())
    private val tabNames = listOf("DAYS", "HOURS")
   // private val workRepository = WorkerRepository()
    private var cities: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cityId.setOnClickListener {
            val myDialog: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
            myDialog.setTitle("Write your city")
            val editCityText = EditText(requireActivity())
            editCityText.inputType = InputType.TYPE_CLASS_TEXT
            myDialog.setView(editCityText)
            myDialog.setPositiveButton("Ok") { _, _ ->
                cities = editCityText.text.toString()
                try {
                    viewModel.loadCity(cities as String)
                    sharedViewModel.setCity(cities as String) //"Времянка". Позже подправлю.

                } catch (e: Exception) {
                    Log.d("Error", e.toString())
                }
            }
            myDialog.show()
        }
        binding.buttonId.setOnClickListener {
            cities?.let { it1 -> viewModel.loadCity(it1) }
            cities?.let { it1 -> sharedViewModel.setCity(it1) }
        }

        viewModel.currentWeather.observe(this.viewLifecycleOwner, Observer {
            // println("Test: $it")
            with(binding)
            {
                tempId.text = it?.temp.toString()
                cityName.text = it?.city
                weatherIcon.load("https:${it?.icon}")
                weatherDate.text = it?.date
                weatherInfo.text = it?.info
                tvMaxMin.text = "${it?.maxTemp}°/${
                    it?.minTemp
                }°"
            }
        })
        val adapter = VPAdapter(fa = requireActivity(), fragList)
        binding.vp.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.vp) { tab, pos ->
            tab.text = tabNames[pos]
        }.attach()
       /* WorkManager.getInstance(requireActivity()).enqueueUniquePeriodicWork(
            "WeatherUpdate",
            ExistingPeriodicWorkPolicy.KEEP, workRepository.periodicWork
        )*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
       // WorkManager.getInstance(requireActivity()).cancelWorkById(workRepository.periodicWork.id)
    }
}