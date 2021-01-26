package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ForecastTemperature(
	@SerializedName("min")
	val min: Float,

	@SerializedName("max")
	val max: Float
)
