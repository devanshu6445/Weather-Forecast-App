package com.weatherforecast.datasource

import com.weatherforecast.database.WeatherDatabase
import com.weatherforecast.model.WeatherDetail
import javax.inject.Inject

class LocalWeatherDataSource @Inject constructor(
    private val database: WeatherDatabase
) {
    fun getAllWeatherDetails() = database.getWeatherDao().getAllWeatherDetails()
    fun insertWeatherDetails(weatherDetail: WeatherDetail) =
        database.getWeatherDao().insertSingleWeatherDetails(weatherDetail)
}