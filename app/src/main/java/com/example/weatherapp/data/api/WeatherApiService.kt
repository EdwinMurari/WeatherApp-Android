package com.example.weatherapp.data.api

import com.example.weatherapp.data.model.City
import com.example.weatherapp.data.model.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

	@GET("weather")
	suspend fun getCurrentWeather(
		@Query("id") id: String,
		@Query("appid") apiKey: String,
		@Query("units") unit: String
	): City

	@GET("forecast")
	suspend fun getWeatherForecast(
		@Query("id") id: String,
		@Query("appid") apiKey: String,
		@Query("cnt") daysCount: Int,
		@Query("units") unit: String
	): Forecast
}