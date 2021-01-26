package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weatherapp.data.api.RetrofitBuilder
import com.example.weatherapp.data.api.WeatherApiHelper
import com.example.weatherapp.data.model.City
import com.example.weatherapp.ui.base.ViewModelFactory
import com.example.weatherapp.ui.main.MainViewModel
import com.example.weatherapp.utils.Status

class MainActivity : AppCompatActivity() {

	private val Any.TAG: String
		get() {
			val tag = javaClass.simpleName
			return if (tag.length <= 23) tag else tag.substring(0, 23)
		}

	private lateinit var viewModel: MainViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		setupViewModel()
		setupObservers()
	}

	private fun setupViewModel() {
		viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(WeatherApiHelper(RetrofitBuilder.weatherApiService))
        ).get(MainViewModel::class.java)
	}

	private fun setupObservers() {
		viewModel.getCurrentWeather().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { city -> test(city) }
                    }
                    Status.ERROR -> {
                        Log.e(TAG, " ${it.message}")
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
	}

	private fun test(city: City) {
		Log.e("MainActivity", "Retrieved test: ${city.name}")
	}
}