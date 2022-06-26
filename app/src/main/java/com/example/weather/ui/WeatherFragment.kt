package com.example.weather.ui

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.weather.adapters.VPAdapter
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.viewmodel.ViewModelDays
import com.google.android.material.tabs.TabLayoutMediator

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: ViewModelDays by activityViewModels()
    private val fragList = listOf(FragmentDays.newInstance(), FragmentHours.newInstance())
    private val tabNames = listOf("DAYS", "HOURS")
    private var cities: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        if (savedInstanceState != null) {
            // binding.cityId.hint = ""
            binding.cityName.text = ""
            binding.weatherInfo.text = ""
        }
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
                sharedViewModel.setCity(cities as String)
            }
            myDialog.show()
        }
        binding.buttonId.setOnClickListener {
            sharedViewModel.setCity(cities as String)
        }

        sharedViewModel.forecastDays.observe(this.viewLifecycleOwner)
        {
            with(binding)
            {
                tempId.text = it.current?.tempC.toString().plus("°")
                cityName.text = it.location?.name
                weatherIcon.load("https:${it.current?.condition?.icon}")
                weatherDate.text = it.location?.localtime
                weatherInfo.text = it.current?.condition?.text
                tvMaxMin.text = "${it.forecast?.forecastday?.get(0)?.day?.maxtempC}°/${
                    it.forecast?.forecastday?.get(0)?.day?.mintempC
                }°"
            }
        }
        val adapter = VPAdapter(requireActivity(), fragList)
        binding.vp.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.vp) { tab, pos ->
            tab.text = tabNames[pos]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // outState.putString("editText", binding.cityId.text.toString())
    }

}