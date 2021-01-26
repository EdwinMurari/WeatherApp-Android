package com.example.weatherapp.data.api

import retrofit2.http.Query

class WeatherApiHelper(private val apiService: WeatherApiService) {

	suspend fun getCurrentWeather(@Query("id") cityId: String, @Query("appid") apiKey: String) = apiService.getCurrentWeather(cityId, apiKey)
}