package com.weatherforecast.datasource

import com.weatherforecast.service.WeatherApi
import javax.inject.Inject

class RemoteWeatherDataSource @Inject constructor(
    private val weatherApi: WeatherApi
) {
    suspend fun getWeatherDetails(
        lat:Double,
        lon:Double
    ) = weatherApi.getSingleWeatherDetail(lat,lon)

    suspend fun getWeatherDetailByCityName(cityName:String) = weatherApi.getWeatherDetailsByCityName(cityName)
}