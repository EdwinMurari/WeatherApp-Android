package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class City(
	@SerializedName("id")
	val id: String,

	@SerializedName("weather")
	val weather: Weather,

	@SerializedName("main")
	val temperature: Temperature
)