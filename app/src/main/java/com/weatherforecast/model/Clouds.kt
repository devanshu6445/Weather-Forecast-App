package com.weatherforecast.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class Clouds (

  @SerializedName("all" ) var all : Int? = null

)