package com.example.weather.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weather.R


class VPAdapter(private val list:List<WeatherModel>): RecyclerView.Adapter<VPAdapter.PagerVH>() {

    class PagerVH(itemView: View):RecyclerView.ViewHolder(itemView)
    {
       private val forecastDate:TextView = itemView.findViewById(R.id.forecastDate)
        private val forecastInfo:TextView= itemView.findViewById(R.id.forecastInfo)
        private val forecastTemp:TextView = itemView.findViewById(R.id.forecastTemp)
        private val forecastIcon: ImageView = itemView.findViewById(R.id.forecastImg)

        fun bind(weather:WeatherModel)
        {
            forecastDate.text = weather.date
            forecastInfo.text = weather.info
            forecastTemp.text = weather.forecastMaxTemp.toString()+"°"+"/"+weather.forecastMinTemp+"°"
            forecastIcon.load("https:${weather.forecastImg}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        val weatherDays:WeatherModel = list[position]
        holder.bind(weatherDays)
    }

    override fun getItemCount(): Int = 2
}


