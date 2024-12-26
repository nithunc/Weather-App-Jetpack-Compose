package com.example.realtimeweather.api




data class Hour(
    val chanceOfRain: Int,
    val chanceOfSnow: Int,
    val cloud: Int,
    val condition: Condition,
    val dewpointC: String,
    val dewpointF: String,
    val feelslikeC: String,
    val feelslikeF: String,
    val gustKph: String,
    val gustMph: String,
    val heatindexC: String,
    val heatindexF: String,
    val humidity: Int,
    val isDay: Int,
    val precipIn: String,
    val precipMm: String,
    val pressureIn: String,
    val pressureMb: String,
    val temp_c: String,
    val tempF: String,
    val time: String,
    val timeEpoch: Int,
    val uv: String,
    val visKm: String,
    val visMiles: String,
    val willItRain: Int,
    val willItSnow: Int,
    val windDegree: Int,
    val windDir: String,
    val windKph: String,
    val windMph: String,
    val windchillC: String,
    val windchillF: String
)