package com.example.realtimeweather.api



data class Forecastday(
    val astro: Astro,
    val date: String,
    val dateEpoch: Int,
    val day: Day,
    val hour: List<Hour>
)