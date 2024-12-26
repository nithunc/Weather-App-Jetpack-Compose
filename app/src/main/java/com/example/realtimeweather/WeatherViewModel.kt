package com.example.realtimeweather

import android.util.Log
import androidx.core.util.Consumer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimeweather.api.Constant
import com.example.realtimeweather.api.FutureWeatherResponse
import com.example.realtimeweather.api.NetWorkResponse
import com.example.realtimeweather.api.RetrofitInstance
import com.example.realtimeweather.api.WeatherResponse
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi

    private val _weatherDataResponse = MutableLiveData<NetWorkResponse<WeatherResponse>>()
    val weatherDataResponse: LiveData<NetWorkResponse<WeatherResponse>> = _weatherDataResponse

    private val _futureWeatherResponse = MutableLiveData<NetWorkResponse<FutureWeatherResponse>>()
    val futureWeatherResponse: LiveData<NetWorkResponse<FutureWeatherResponse>> = _futureWeatherResponse

    fun getData(city: String) {
        _weatherDataResponse.value = NetWorkResponse.Loading
        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(Constant.apiKey, city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherDataResponse.value = NetWorkResponse.Success(it)
                    }
                } else {
                    response.body()?.let {
                        _weatherDataResponse.value = NetWorkResponse.Error("Failed to load")
                    }
                }
            } catch (e: Exception) {
                _weatherDataResponse.value = NetWorkResponse.Error("Failed to load")
            }
        }
    }
    fun getFutureData(city: String,currentDate: String) {
        viewModelScope.launch {
            try {
                val response = weatherApi.getFutureWeather(Constant.apiKey, city,currentDate)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _futureWeatherResponse.value = NetWorkResponse.Success(it)
                    }
                } else {
                    response.body()?.let {
                        _futureWeatherResponse.value = NetWorkResponse.Error("Failed to load")
                    }
                }
            } catch (e: Exception) {
                _futureWeatherResponse.value = NetWorkResponse.Error("Failed to load")
            }
        }
    }
}