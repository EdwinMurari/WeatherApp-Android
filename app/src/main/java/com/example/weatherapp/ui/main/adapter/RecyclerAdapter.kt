package com.example.weatherapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.Forecast
import com.example.weatherapp.data.model.ForecastData
import com.example.weatherapp.ui.main.view.ForecastItemView
import com.example.weatherapp.utils.DateUtil
import com.example.weatherapp.utils.StringUtil

class RecyclerAdapter(private val foreCastData: ArrayList<ForecastData>) :
	RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

	override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
		val view = LayoutInflater.from(viewGroup.context)
			.inflate(R.layout.item_forecast, viewGroup, false)

		return ViewHolder(view)
	}

	override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
		val hiLoString = StringUtil.getTemperatureString(foreCastData[i].temperature.tempMax) +
				StringUtil.DEGREE_SYMBOL +
				StringUtil.HI_LOW_SEPARATOR +
				StringUtil.getTemperatureString(foreCastData[i].temperature.tempMin) +
				StringUtil.DEGREE_SYMBOL;

		viewHolder.forecastDate.text = DateUtil.getDateTime(foreCastData[i].date)
		viewHolder.weatherConditionTV.text = foreCastData[i].weather[0].condition
		viewHolder.temperatureHiLoTV.text = hiLoString
	}

	override fun getItemCount(): Int {
		return foreCastData.size
	}

	fun updateItems(foreCastData: List<ForecastData>) {
		this.foreCastData.apply {
			clear()
			addAll(foreCastData)
		}
	}

	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		var forecastDate: TextView
		var weatherConditionTV: TextView
		var temperatureHiLoTV: TextView

		init {
			forecastDate = itemView.findViewById<TextView>(R.id.forecast_date)
			weatherConditionTV = itemView.findViewById<TextView>(R.id.forecast_weatherCondition_tv)
			temperatureHiLoTV = itemView.findViewById<TextView>(R.id.forecast_temperatureHiLo_tv)
		}
	}
}