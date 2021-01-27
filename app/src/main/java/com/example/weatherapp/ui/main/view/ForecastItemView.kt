package com.example.weatherapp.ui.main.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.weatherapp.R
import com.example.weatherapp.data.model.ForecastData
import com.example.weatherapp.utils.DateUtil
import com.example.weatherapp.utils.StringUtil
import java.text.SimpleDateFormat
import java.util.*

class ForecastItemView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyle: Int = 0,
	defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

	private var forecastDate: TextView
	private var weatherConditionTV: TextView
	private var temperatureHiLoTV: TextView

	init {
		val view = LayoutInflater.from(context)
			.inflate(R.layout.item_forecast, this, true)

		forecastDate = view.findViewById<TextView>(R.id.forecast_date)
		weatherConditionTV = view.findViewById<TextView>(R.id.forecast_weatherCondition_tv)
		temperatureHiLoTV = view.findViewById<TextView>(R.id.forecast_temperatureHiLo_tv)
	}

	fun setupUpData(forecastData: ForecastData) {
		val hiLoString = StringUtil.getTemperatureString(forecastData.temperature.tempMax) +
				DEGREE_SYMBOL +
				SEPARATOR +
				StringUtil.getTemperatureString(forecastData.temperature.tempMin) +
				DEGREE_SYMBOL;

		forecastDate.text = DateUtil.getDateTime(forecastData.date)
		weatherConditionTV.text = forecastData.weather[0].condition
		temperatureHiLoTV.text = hiLoString
	}

	companion object {
		private const val DEGREE_SYMBOL = "°";
		private const val SEPARATOR = " / ";
	}
}