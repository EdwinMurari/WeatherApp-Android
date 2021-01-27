package com.example.weatherapp.data.api

import retrofit2.http.Query

class WeatherApiHelper(private val apiService: WeatherApiService) {

	suspend fun getCurrentWeather(cityId: String, apiKey: String, unit: String) =
		apiService.getCurrentWeather(cityId, apiKey, unit)

	suspend fun getWeatherForecast(cityId: String, apiKey: String, daysCount: Int, units: String, mode: String) =
		apiService.getWeatherForecast(cityId, apiKey, daysCount, units, mode)
}