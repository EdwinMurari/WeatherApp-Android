package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.WeatherApiHelper
import retrofit2.http.Query

class MainRepository(private val apiHelper: WeatherApiHelper) {

	suspend fun getCurrentWeather(cityId: String, apiKey: String, unit: String) =
		apiHelper.getCurrentWeather(cityId, apiKey, unit)

	suspend fun getWeatherForecast(cityId: String, apiKey: String, daysCount: Int, units: String, mode: String) =
		apiHelper.getWeatherForecast(cityId, apiKey, daysCount, units, mode)
}