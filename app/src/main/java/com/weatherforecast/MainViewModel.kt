package com.weatherforecast

import android.location.Location
import androidx.lifecycle.ViewModel
import com.weatherforecast.model.Coord
import com.weatherforecast.model.WeatherDetail
import com.weatherforecast.usecase.GetWeatherDataUseCase
import com.weatherforecast.usecase.GetWeatherUCParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWeatherDataUseCase: GetWeatherDataUseCase
) : ViewModel() {

    suspend fun getWeatherDetails(
        location: Location?
    ): Flow<List<WeatherDetail>> {

        return getWeatherDataUseCase.process(GetWeatherUCParams(Coord(
            location?.longitude,
            location?.latitude
        )))
    }
}