package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class Temperature(
	@SerializedName("temp")
	val current: Float,

	@SerializedName("feels_like")
	val feelsLike: Float,

	@SerializedName("temp_min")
	val tempMin: Float,

	@SerializedName("temp_max")
	val tempMax: Float,

	@SerializedName("pressure")
	val pressure: Int,

	@SerializedName("humidity")
	val humidity: Int
)
