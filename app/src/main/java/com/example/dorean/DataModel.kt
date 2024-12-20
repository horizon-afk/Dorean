package com.example.dorean

import android.util.Log
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun getGameData() {
    val request = APIClient.apiService.getResponse()
    request.enqueue(object : Callback<JsonObject> {
        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
            if (response.isSuccessful && response.body() != null) {
                // on success
                Log.d("LOCATION", "http call successfully")
                GameModelList.getGameData(response.body()!!)
            } else {
                // on error
            }
        }

        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            // on connection error
        }
        
    })
    
}
