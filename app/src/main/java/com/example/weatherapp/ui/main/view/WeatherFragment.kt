package com.example.weatherapp.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
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
		val temperatureHighLowTV = view.findViewById<TextView>(R.id.temperatureHiLo_tv)
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
		val cityId = arguments?.getString(KEY_CITY_ID) ?: return;

		initViewModel()

		val forecastLayout = view.findViewById<ConstraintLayout>(R.id.forecastData_layout)
		val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

		val forecast1DataView = view.findViewById<ForecastItemView>(R.id.forecast1_view)
		val forecast2DataView = view.findViewById<ForecastItemView>(R.id.forecast2_view)
		val forecast3DataView = view.findViewById<ForecastItemView>(R.id.forecast3_view)

		viewModel.getWeatherForecast(cityId, 4).observe(this, Observer {
			it?.let { resource ->
				when (resource.status) {
					Status.SUCCESS -> {
						forecastLayout.visibility = View.VISIBLE
						progressBar.visibility = View.GONE

						resource.data?.let { forecastData ->
							updateForecastData(
								arrayOf(
									forecast1DataView,
									forecast2DataView,
									forecast3DataView
								), forecastData
							)
						}
					}
					Status.ERROR -> {
						forecastLayout.visibility = View.VISIBLE
						progressBar.visibility = View.GONE

						Log.e(TAG, " ${it.message}")
					}
					Status.LOADING -> {

						forecastLayout.visibility = View.INVISIBLE
						progressBar.visibility = View.VISIBLE
					}
				}
			}
		})
	}

	private fun initViewModel() {
		viewModel = ViewModelProviders.of(
			this,
			ViewModelFactory(WeatherApiHelper(RetrofitBuilder.weatherApiService))
		).get(MainViewModel::class.java)
	}

	private fun updateForecastData(
		forecastDataViews: Array<ForecastItemView>,
		forecastData: Forecast
	) {
		forecastDataViews[0].setupUpData(forecastData.data[1])
		forecastDataViews[1].setupUpData(forecastData.data[2])
		forecastDataViews[2].setupUpData(forecastData.data[3])
	}

	companion object {

		private const val KEY_CITY_NAME = "cityName"
		private const val KEY_CITY_ID = "cityId"
		private const val KEY_TEMPERATURE = "temperature"
		private const val KEY_TEMPERATURE_HI_LO = "temperatureHiLo"
		private const val KEY_CONDITION = "condition"

		private const val DEGREE_SYMBOL = "°";
		private const val SEPARATOR = " / ";

		fun newInstance(city: City): WeatherFragment {

			val args = Bundle()
			args.putString(KEY_CITY_NAME, city.name)
			args.putString(KEY_CITY_ID, city.id)
			args.putString(
				KEY_TEMPERATURE,
				StringUtil.getTemperatureString(city.temperature.current)
			)
			args.putString(
				KEY_TEMPERATURE_HI_LO, (
						StringUtil.getTemperatureString(city.temperature.tempMax) +
								DEGREE_SYMBOL +
								SEPARATOR +
								StringUtil.getTemperatureString(city.temperature.tempMin) +
								DEGREE_SYMBOL)
			)
			args.putString(KEY_CONDITION, city.weather[0].condition)

			val fragment = WeatherFragment()
			fragment.arguments = args

			return fragment
		}
	}

}