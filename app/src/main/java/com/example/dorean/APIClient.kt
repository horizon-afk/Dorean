package com.example.dorean

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitClient {
    private const val BASE_URL = "https://store-site-backend-static-ipv4.ak.epicgames.com/freeGamesPromotions/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
}

object APIClient {
    val apiService:ApiService by lazy { RetroFitClient.retrofit.create(ApiService::class.java) }
}