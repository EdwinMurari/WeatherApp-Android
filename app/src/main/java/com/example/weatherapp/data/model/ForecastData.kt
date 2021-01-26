package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ForecastData(
	@SerializedName("weather")
	val weather: List<Weather>,

	@SerializedName("temp")
	val temperature: ForecastTemperature
)