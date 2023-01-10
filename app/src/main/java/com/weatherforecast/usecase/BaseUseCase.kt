package com.weatherforecast.usecase

interface BaseUseCase {
    fun process()
}

interface BaseUseCaseWithInput<in I:Any> {
    fun process(input: I)
}
interface BaseUseCaseWithInputOutput<in I:Any, O:Any>{
    suspend fun process(input :I):O
}