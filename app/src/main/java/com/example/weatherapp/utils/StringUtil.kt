package com.example.weatherapp.utils

object StringUtil {

	const val DEGREE_SYMBOL = "°";
	const val HI_LOW_SEPARATOR = " / ";

	fun getTemperatureString(temperature: Float): String {
		return temperature.toInt().toString()
	}

}