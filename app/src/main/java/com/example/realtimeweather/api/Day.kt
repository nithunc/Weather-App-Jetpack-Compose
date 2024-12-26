package com.example.realtimeweather.api

data class Day(
    val avghumidity: String,
    val avgtempC: String,
    val avgtempF: String,
    val avgvisKm: String,
    val avgvisMiles: String,
    val condition: Condition,
    val maxtempC: String,
    val maxtempF: String,
    val maxwindKph: String,
    val maxwindMph: String,
    val mintempC: String,
    val mintempF: String,
    val totalprecipIn: String,
    val totalprecipMm: String,
    val uv: String
)