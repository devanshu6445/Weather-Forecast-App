package com.weatherforecast.usecase

import com.weatherforecast.model.Coord
import com.weatherforecast.model.WeatherDetail
import com.weatherforecast.repository.GetWeatherDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(
    private val getWeatherDataRepository: GetWeatherDataRepository
) : BaseUseCaseWithInputOutput<GetWeatherUCParams,Flow<List<WeatherDetail>>>{
    override suspend fun process(input: GetWeatherUCParams): Flow<List<WeatherDetail>> {
       return  getWeatherDataRepository.getWeatherDetails(input)
    }
}

data class GetWeatherUCParams(
    val coord: Coord
)