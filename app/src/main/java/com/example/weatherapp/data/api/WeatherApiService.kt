package com.example.weatherapp.data.api

import com.example.weatherapp.data.model.City
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

	@GET("weather")
	suspend fun getCurrentWeather(
		@Query("id") id: String,
		@Query("appid") apiKey: String,
		@Query("units") unit: String
	): City

}