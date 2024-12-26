package com.example.realtimeweather.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/v1/current.json?")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String
    ): Response<WeatherResponse>

    @GET("/v1/future.json?")
    suspend fun getFutureWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String,
        @Query("dt") date: String
    ): Response<FutureWeatherResponse>
}