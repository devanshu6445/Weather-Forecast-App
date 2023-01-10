package com.weatherforecast

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.weatherforecast.databinding.ItemCityWeatherBinding
import com.weatherforecast.model.WeatherDetail

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private var dataList : List<WeatherDetail> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(parent)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data:List<WeatherDetail>){
        dataList = data
        notifyDataSetChanged()
    }
    inner class WeatherViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemCityWeatherBinding>(
            ItemCityWeatherBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        ) {
            fun bind(weatherDetail: WeatherDetail){
                binding.cityName.text = weatherDetail.name?:""
                binding.temprature.text = "${weatherDetail.temp} °F"
                binding.desc.text  = weatherDetail.description
                binding.isCurrentLocation.isVisible = weatherDetail.isCurrentLocation
            }
        }
}

abstract class BaseViewHolder<VB : ViewDataBinding>(protected val binding: VB) :
    RecyclerView.ViewHolder(binding.root)