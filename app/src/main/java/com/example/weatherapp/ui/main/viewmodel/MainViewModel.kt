package com.example.weatherapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.weatherapp.data.model.City
import com.example.weatherapp.data.repository.MainRepository
import com.example.weatherapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

	fun getCurrentWeather() = liveData(Dispatchers.IO) {
		emit(Resource.loading(data = null))
		try {
			emit(Resource.success(data = mainRepository.getCurrentWeather("2158177", "565c6f32012c8b89d9f0b59dfd5c77af")))
		} catch (exception: Exception) {
			emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
		}
	}
}