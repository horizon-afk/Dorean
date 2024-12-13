package com.example.dorean

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET(".")
    fun getResponse(): Call<JsonObject>
}