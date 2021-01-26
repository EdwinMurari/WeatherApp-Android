package com.example.weatherapp.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.weatherapp.data.model.City
import com.example.weatherapp.ui.main.view.WeatherFragment

class ViewPagerAdapter2(fragmentManager: FragmentManager, private val cityList: ArrayList<City>) :
	FragmentStatePagerAdapter(fragmentManager) {

	override fun getItem(position: Int): Fragment {
		return WeatherFragment.newInstance(cityList[position])
	}

	override fun getCount(): Int {
		return cityList.size
	}
}