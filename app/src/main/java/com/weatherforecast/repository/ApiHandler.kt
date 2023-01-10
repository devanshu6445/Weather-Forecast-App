package com.weatherforecast.repository

import com.weatherforecast.model.WeatherDetail
import retrofit2.Response

class ApiHandler {

    fun <T : Any> handleApi(action: () -> Response<T>) {}

}