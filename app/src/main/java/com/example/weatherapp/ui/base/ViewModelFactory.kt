package com.example.weatherapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.api.WeatherApiHelper
import com.example.weatherapp.data.repository.MainRepository
import com.example.weatherapp.ui.main.MainViewModel

class ViewModelFactory(private val apiHelper: WeatherApiHelper) : ViewModelProvider.Factory {

	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
			return MainViewModel(MainRepository(apiHelper)) as T
		}
		throw IllegalArgumentException("Unknown class name")
	}

}