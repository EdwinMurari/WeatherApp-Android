package com.example.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.*


object DateUtil {

	fun getDateTime(epochTime: Long): String {
		val sdf = SimpleDateFormat("EEE, d MMM", Locale.getDefault())
		return sdf.format(Date(epochTime * 1000))
	}

}