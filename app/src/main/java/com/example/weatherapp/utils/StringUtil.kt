package com.example.weatherapp.utils

object StringUtil {
	fun getTemperatureString(temperature: Float): String {
		return temperature.toInt().toString()
	}
}