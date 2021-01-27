package com.example.weatherapp.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.api.RetrofitBuilder
import com.example.weatherapp.data.api.WeatherApiHelper
import com.example.weatherapp.data.model.Forecast
import com.example.weatherapp.ui.base.ViewModelFactory
import com.example.weatherapp.ui.main.adapter.RecyclerAdapter
import com.example.weatherapp.ui.main.viewmodel.MainViewModel
import com.example.weatherapp.utils.Status

class MoreForecastActivity : AppCompatActivity() {

	private lateinit var viewModel: MainViewModel
	private lateinit var cityId: String
	private lateinit var progressBar: ProgressBar
	private lateinit var recyclerView: RecyclerView
	private lateinit var adapter: RecyclerAdapter

	private val Any.TAG: String
		get() {
			val tag = javaClass.simpleName
			return if (tag.length <= 23) tag else tag.substring(0, 23)
		}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_more_forecast)

		getIntentData()
		initViewModel()
		initUi()
		setupObservers()
	}

	private fun initViewModel() {
		viewModel = ViewModelProviders.of(
			this,
			ViewModelFactory(WeatherApiHelper(RetrofitBuilder.weatherApiService))
		).get(MainViewModel::class.java)
	}

	private fun initUi() {
		recyclerView = findViewById(R.id.recyclerView)
		progressBar = findViewById(R.id.progressBar)

		recyclerView.layoutManager = LinearLayoutManager(this)
		adapter = RecyclerAdapter(arrayListOf())
		recyclerView.adapter = adapter
	}

	private fun setupObservers() {
		viewModel.getWeatherForecast(cityId, 15).observe(this, Observer {
			it?.let { resource ->
				when (resource.status) {
					Status.SUCCESS -> {
						recyclerView.visibility = View.VISIBLE
						progressBar.visibility = View.GONE

						resource.data?.let { forecastData -> updateList(forecastData) }
					}
					Status.ERROR -> {
						recyclerView.visibility = View.VISIBLE
						progressBar.visibility = View.GONE

						Log.e(TAG, " ${it.message}")
					}
					Status.LOADING -> {

						recyclerView.visibility = View.GONE
						progressBar.visibility = View.VISIBLE
					}
				}
			}
		})
	}

	private fun getIntentData() {
		cityId = intent.getStringExtra(WeatherFragment.INTENT_EXTRA_CITY_ID).toString()
	}

	private fun updateList(forecast: Forecast) {
		adapter.apply {
			updateItems(forecast.data)
			notifyDataSetChanged()
		}
	}
}