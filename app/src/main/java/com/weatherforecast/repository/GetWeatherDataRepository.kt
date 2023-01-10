package com.weatherforecast.repository

import com.weatherforecast.datasource.LocalWeatherDataSource
import com.weatherforecast.datasource.RemoteWeatherDataSource
import com.weatherforecast.model.WeatherDetail
import com.weatherforecast.model.toWeatherDetails
import com.weatherforecast.usecase.GetWeatherUCParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetWeatherDataRepository @Inject constructor(
    private val localWeatherDataSource: LocalWeatherDataSource,
    private val remoteWeatherDataSource: RemoteWeatherDataSource
) : BaseRepository() {

    fun getWeatherDetails(input: GetWeatherUCParams): Flow<List<WeatherDetail>> {
        val cityList = arrayListOf(
            "New York",
            "Singapore",
            "Mumbai",
            "Delhi",
            "Sydney",
            "Melbourne"
        )
        ioScope.launch {
            val weatherResponse = input.coord.lon?.let { it1 ->
                input.coord.lat?.let { it2 ->
                    remoteWeatherDataSource.getWeatherDetails(
                        it2, it1
                    )
                }
            }
            if(weatherResponse?.body()!=null){
                localWeatherDataSource.insertWeatherDetails(weatherResponse.body()!!.toWeatherDetails(true))
            }
        }
        ioScope.launch {
            cityList.forEach {
                val weatherResponse = remoteWeatherDataSource.getWeatherDetailByCityName(it)
                if(weatherResponse.body() != null){
                    localWeatherDataSource.insertWeatherDetails(weatherResponse.body()!!.toWeatherDetails(false))
                }
            }
        }
        return localWeatherDataSource.getAllWeatherDetails()
    }
}