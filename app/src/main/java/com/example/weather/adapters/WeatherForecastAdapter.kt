package com.example.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weather.data.WeatherModelDate
import com.example.weather.databinding.ItemViewBinding

class WeatherForecastAdapter(var items: MutableList<WeatherModelDate>) :
    ListAdapter<WeatherModelDate, WeatherForecastAdapter.ViewHolder>(DiffCallback()) {
    private lateinit var binding: ItemViewBinding

    inner class ViewHolder(itemView: ItemViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        fun bind(item: WeatherModelDate) {
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

private class DiffCallback : DiffUtil.ItemCallback<WeatherModelDate>() {

    private val payload = Any()

    override fun areItemsTheSame(oldItem: WeatherModelDate, newItem: WeatherModelDate): Boolean =
        (oldItem.date == newItem.date)

    override fun areContentsTheSame(oldItem: WeatherModelDate, newItem: WeatherModelDate): Boolean =
        (oldItem == newItem)

    override fun getChangePayload(oldItem: WeatherModelDate, newItem: WeatherModelDate): Any =
        payload
}