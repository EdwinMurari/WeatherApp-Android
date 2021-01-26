package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class Weather(
	@SerializedName("id")
	val id: String,

	@SerializedName("main")
	val condition: String,

	@SerializedName("description")
	val desc: String,

	@SerializedName("icon")
	val icon: String
)