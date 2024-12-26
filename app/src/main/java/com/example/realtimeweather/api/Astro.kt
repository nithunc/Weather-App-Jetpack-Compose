package com.example.realtimeweather.api




data class Astro(
    val moonIllumination: Int,
    val moonPhase: String,
    val moonrise: String,
    val moonset: String,
    val sunrise: String,
    val sunset: String
)