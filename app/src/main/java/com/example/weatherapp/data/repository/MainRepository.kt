package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.WeatherApiHelper
import retrofit2.http.Query

class MainRepository(private val apiHelper: WeatherApiHelper) {

	suspend fun getCurrentWeather(@Query("id") cityId: String, @Query("appid") apiKey: String, @Query("units") unit: String) = apiHelper.getCurrentWeather(cityId, apiKey, unit)
}