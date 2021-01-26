package com.example.weatherapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.weatherapp.data.model.City
import com.example.weatherapp.data.repository.MainRepository
import com.example.weatherapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

	fun getCurrentWeatherById() = liveData(Dispatchers.IO) {
		emit(Resource.loading(data = null))
		try {
			emit(Resource.success(data = mainRepository.getCurrentWeather(CITYID_MELBOURNE, API_KEY, UNIT_METRIC)))
		} catch (exception: Exception) {
			emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
		}
	}

	fun getCurrentWeatherByIds(cityIds: Array<String>) = liveData(Dispatchers.IO) {
		emit(Resource.loading(data = null))
		try {
			val cityList: MutableList<City> = mutableListOf()
			for (cityId in cityIds) {
				cityList.add(mainRepository.getCurrentWeather(cityId, API_KEY, UNIT_METRIC))
			}

			emit(Resource.success(data = cityList))
		} catch (exception: Exception) {
			emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
		}
	}

	companion object{
		const val API_KEY: String = "565c6f32012c8b89d9f0b59dfd5c77af"
		const val CITYID_MELBOURNE: String = "2158177"
		val CITYID_LIST: Array<String> = arrayOf("2147714", "2063523", "2163355")
		const val UNIT_METRIC = "metric"
	}
}