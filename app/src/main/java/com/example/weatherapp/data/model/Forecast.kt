package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class Forecast(
	@SerializedName("list")
	val data: List<ForecastData>
)