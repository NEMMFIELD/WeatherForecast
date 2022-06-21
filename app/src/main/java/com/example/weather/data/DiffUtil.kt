package com.example.weather.data

import androidx.recyclerview.widget.DiffUtil

class DiffUtil(): DiffUtil.ItemCallback<WeatherModelHours>() {
    override fun areItemsTheSame(oldItem: WeatherModelHours, newItem: WeatherModelHours): Boolean {
       return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(
        oldItem: WeatherModelHours,
        newItem: WeatherModelHours
    ): Boolean {
      return oldItem == newItem
    }
}