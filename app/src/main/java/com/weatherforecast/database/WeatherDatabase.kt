package com.weatherforecast.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.weatherforecast.model.WeatherDetail

@Database(entities = [WeatherDetail::class], version = 1)
abstract class WeatherDatabase : RoomDatabase(){

    abstract fun getWeatherDao(): WeatherDao
}