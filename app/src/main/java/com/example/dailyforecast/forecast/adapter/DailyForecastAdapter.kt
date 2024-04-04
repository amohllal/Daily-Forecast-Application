package com.example.dailyforecast.forecast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyforecast.databinding.ItemDailyForecastBinding
import com.example.domain.model.DailyForecastResponse

class DailyForecastAdapter(
    var list: ArrayList<DailyForecastResponse>
) : RecyclerView.Adapter<DailyForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDailyForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val binding: ItemDailyForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = list[position]
            binding.apply {
                tempTv.text = item.temp
                seaLevelTv.text = item.seaLevel
                weatherTitleTv.text = item.weatherTitle
                windDegreeTv.text = item.windDegree
                windSpeedTv.text = item.windSpeed
                weatherDescriptionTv.text = item.weatherDescription
            }
        }
    }
}