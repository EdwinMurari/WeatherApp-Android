package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ForecastTemperature(
	@SerializedName("min")
	val tempMin: Float,

	@SerializedName("max")
	val tempMax: Float
)
