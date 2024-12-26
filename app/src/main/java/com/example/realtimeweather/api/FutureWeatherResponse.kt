package com.example.realtimeweather.api





data class FutureWeatherResponse(
    val forecast: Forecast,
    val location: Location
)