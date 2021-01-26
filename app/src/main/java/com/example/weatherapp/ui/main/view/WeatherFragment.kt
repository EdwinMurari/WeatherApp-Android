package com.example.weatherapp.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weatherapp.R
import com.example.weatherapp.data.api.RetrofitBuilder
import com.example.weatherapp.data.api.WeatherApiHelper
import com.example.weatherapp.data.model.City
import com.example.weatherapp.data.model.Forecast
import com.example.weatherapp.ui.base.ViewModelFactory
import com.example.weatherapp.ui.main.viewmodel.MainViewModel
import com.example.weatherapp.ui.main.viewmodel.MainViewModel.Companion.CITYID_MELBOURNE
import com.example.weatherapp.utils.Status
import com.example.weatherapp.utils.StringUtil

class WeatherFragment : Fragment() {

	private val Any.TAG: String
		get() {
			val tag = javaClass.simpleName
			return if (tag.length <= 23) tag else tag.substring(0, 23)
		}

	private lateinit var viewModel: MainViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
		Bundle?
	): View? {

		val view = inflater.inflate(R.layout.fragment_city_weather, container, false)

		val cityNameTV = view.findViewById<TextView>(R.id.cityName_tv)
		val temperatureTV = view.findViewById<TextView>(R.id.temperature_tv)
		val temperatureHighLowTV = view.findViewById<TextView>(R.id.temperatureHighLow_tv)
		val weatherConditionTV = view.findViewById<TextView>(R.id.weatherCondition_tv)

		val args = arguments
		if (args != null) {
			cityNameTV.text = args.getString(KEY_CITY_NAME)
			temperatureTV.text = args.getString(KEY_TEMPERATURE)
			temperatureHighLowTV.text = args.getString(KEY_TEMPERATURE_HI_LO)
			weatherConditionTV.text = args.getString(KEY_CONDITION)
		}

		return view
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initForecastData(view)
	}

	private fun initForecastData(view: View) {
		viewModel = ViewModelProviders.of(
			this,
			ViewModelFactory(WeatherApiHelper(RetrofitBuilder.weatherApiService))
		).get(MainViewModel::class.java)

		viewModel.getWeatherForecast(CITYID_MELBOURNE, 3).observe(this, Observer {
			it?.let { resource ->
				when (resource.status) {
					Status.SUCCESS -> {
						resource.data?.let { forecastData -> updateForecastData(forecastData) }
					}
					Status.ERROR -> {
						Log.e(TAG, " ${it.message}")
					}
					Status.LOADING -> {
					}
				}
			}
		})
	}

	private fun updateForecastData(forecastData: Forecast) {
		Log.e("TEST", "forecastData: ${forecastData.data.size}")
	}

	companion object {

		private const val KEY_CITY_NAME = "cityName"
		private const val KEY_TEMPERATURE = "temperature"
		private const val KEY_TEMPERATURE_HI_LO = "temperatureHiLo"
		private const val KEY_CONDITION = "condition"

		fun newInstance(city: City): WeatherFragment {

			val args = Bundle()
			args.putString(KEY_CITY_NAME, city.name)
			args.putString(
				KEY_TEMPERATURE,
				StringUtil.getTemperatureString(city.temperature.current)
			)
			args.putString(
				KEY_TEMPERATURE_HI_LO, (
						StringUtil.getTemperatureString(city.temperature.tempMax) +
								"\u00B0" +
								" / " +
								StringUtil.getTemperatureString(city.temperature.tempMin) +
								"\u00B0")
			)
			args.putString(KEY_CONDITION, city.weather[0].condition)

			val fragment = WeatherFragment()
			fragment.arguments = args

			return fragment
		}
	}

}