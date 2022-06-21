package com.example.weather.data

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weather.databinding.ItemViewBinding

class WeatherForecastAdapterHours(var items: MutableList<WeatherModelHours>) :
    ListAdapter<WeatherModelHours, WeatherForecastAdapterHours.ViewHolder>(com.example.weather.data.DiffUtil()) {
    private lateinit var binding: ItemViewBinding

    override fun submitList(list: MutableList<WeatherModelHours>?) {
        items = list?.toMutableList()?:ArrayList()
        super.submitList(list)
    }
    inner class ViewHolder(itemView: ItemViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        fun bind(item: WeatherModelHours) {
            binding.apply {
                forecastDate.text = item.date
                forecastInfo.text = item.info
                forecastTemp.text = item.forecastTemp.toString()
                forecastImg.load("https:${item.forecastImg}")
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemViewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


}