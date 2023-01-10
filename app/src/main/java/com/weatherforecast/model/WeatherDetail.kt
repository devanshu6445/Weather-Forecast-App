package com.weatherforecast.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class WeatherDetail(
    val base: String? = null,
    val visibility: Int? = null,
    val description:String? = null,
    val dt: Int? = null,
    val timezone: Int? = null,
    @PrimaryKey(autoGenerate = false) var id: Int? = null,
    val name: String? = null,
    val cod: Int? = null,
    val temp:Double? = null,
    var isCurrentLocation:Boolean = false
)