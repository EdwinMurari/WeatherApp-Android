package com.example.weatherapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.ForecastData
import com.example.weatherapp.utils.StringUtil

class RecyclerAdapter(private val foreCastData: ArrayList<ForecastData>) :
	RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
	override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
		val v = LayoutInflater.from(viewGroup.context)
			.inflate(R.layout.item_forecast, viewGroup, false)
		return ViewHolder(v)
	}

	override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
		val hiLoString =
			StringUtil.getTemperatureString(foreCastData[i].temperature.max) +
					"\u00B0" +
					" / " +
					StringUtil.getTemperatureString(foreCastData[i].temperature.max) +
					"\u00B0";

		viewHolder.weatherConditionTV.text = foreCastData[i].weather[0].condition
		viewHolder.temperatureHighLowTV.text = hiLoString;

	}

	override fun getItemCount(): Int {
		return foreCastData.size
	}

	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		var weatherConditionTV: TextView
		var temperatureHighLowTV: TextView

		init {
			weatherConditionTV = itemView.findViewById(R.id.weatherCondition_tv)
			temperatureHighLowTV = itemView.findViewById(R.id.temperatureHighLow_tv)
		}
	}
}