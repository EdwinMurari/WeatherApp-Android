package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ForecastData(
	@SerializedName("dt")
	val date: Long,

	@SerializedName("weather")
	val weather: List<Weather>,

	@SerializedName("temp")
	val temperature: ForecastTemperature
)