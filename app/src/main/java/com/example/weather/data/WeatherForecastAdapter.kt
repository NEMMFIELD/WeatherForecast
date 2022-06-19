package com.example.weather.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weather.databinding.ItemViewBinding

class WeatherForecastAdapter(val items: MutableList<WeatherModel>) :
    RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder>() {
    private lateinit var binding: ItemViewBinding

    inner class ViewHolder(itemView: ItemViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        fun bind(item: WeatherModel) {
            binding.apply {
                forecastDate.text = item.date
                forecastInfo.text = item.info
                forecastTemp.text =
                    item.forecastMaxTemp.toString() + "°" + "/" + item.forecastMinTemp + "°"
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