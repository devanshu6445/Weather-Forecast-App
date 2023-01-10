package com.weatherforecast.service

import com.weatherforecast.model.WeatherDetail
import com.weatherforecast.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather/")
    suspend fun getSingleWeatherDetail(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("appid") appid:String = WeatherClient.API_KEY
    ): Response<WeatherResponse>

    @GET("data/2.5/weather/")
    suspend fun getWeatherDetailsByCityName(
        @Query("q") name:String,
        @Query("appid") appid:String = WeatherClient.API_KEY
    ): Response<WeatherResponse>
}