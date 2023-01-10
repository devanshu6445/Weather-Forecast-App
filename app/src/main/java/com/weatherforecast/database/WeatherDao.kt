package com.weatherforecast.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.weatherforecast.model.Weather
import com.weatherforecast.model.WeatherDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleWeatherDetails(weatherDetail: WeatherDetail)

    @Query("select * from weatherdetail order by isCurrentLocation DESC")
    fun getAllWeatherDetails():Flow<List<WeatherDetail>>
}