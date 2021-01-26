package com.example.weatherapp.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.data.model.City
import com.example.weatherapp.utils.StringUtil

class WeatherFragment : Fragment() {

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