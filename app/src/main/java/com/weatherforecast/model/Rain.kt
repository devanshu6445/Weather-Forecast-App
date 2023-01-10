package com.weatherforecast.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class Rain (

  @SerializedName("1h" ) var h1 : Double? = null

)