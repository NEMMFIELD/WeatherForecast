package com.example.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weather.data.WeatherModelHours
import com.example.weather.databinding.ItemViewHoursBinding

class WeatherForecastAdapterHours(var items: MutableList<WeatherModelHours>) :
    ListAdapter<WeatherModelHours, WeatherForecastAdapterHours.ViewHolder>(com.example.weather.data.DiffUtilHours()) {
    private lateinit var binding: ItemViewHoursBinding

    inner class ViewHolder(itemView: ItemViewHoursBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        fun bind(item: WeatherModelHours) {
            binding.apply {
                forecastHours.text = item.date
                forecastInfoHours.text = item.info
                forecastTempHours.text = item.forecastTemp.toString()
                forecastImgHours.load("https:${item.forecastImg}")
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(items[position])
        holder.setIsRecyclable(false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemViewHoursBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size
}