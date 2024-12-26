package com.example.realtimeweather.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class WeatherResponse(
    val location: Location,
    val current: Current
) {
    data class Location(
        val name: String? = null,
        val region: String? = null,
        val country: String? = null,
        val lat: String? = null,
        val lon: String? = null,
        val tzId: String? = null,
        val localtimeEpoch: Int? = null,
        val localtime: String
    )

    data class Current(
        val lastUpdatedEpoch: Int? = null,
        val lastUpdated: String? = null,
        val temp_c: String?,
        val tempF: String? = null,
        val isDay: Int? = null,
        val condition: Condition? = null,
        val windMph: String? = null,
        val wind_kph: String? = null,
        val windDegree: Int? = null,
        val wind_dir: String? = null,
        val pressureMb: String? = null,
        val pressureIn: String? = null,
        val precip_mm: String? = null,
        val precipIn: String? = null,
        val humidity: Int? = null,
        val cloud: Int? = null,
        val feelslikeC: String? = null,
        val feelslikeF: String? = null,
        val windchillC: String? = null,
        val windchillF: String? = null,
        val heatindexC: String? = null,
        val heatindexF: String? = null,
        val dewpointC: String? = null,
        val dewpointF: String? = null,
        val visKm: String? = null,
        val visMiles: String? = null,
        val uv: String? = null,
        val gustMph: String? = null,
        val gustKph: String? = null
    ) {
        data class Condition(
            val text: String? = null,
            val icon: String,
            val code: Int? = null
        )
    }
}