package com.kasai.speed_whether.repository

import com.kasai.speed_whether.model.WeatherInfo
import com.kasai.speed_whether.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    @GET("data/2.5/onecall?")
    suspend fun getWeatherInfo(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") exclude: String,
        @Query("appid") appID: String
    ): Response<WeatherInfo>
}
