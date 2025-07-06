package com.example.dorean

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun requestData(onResult: (List<GameModel>) -> Unit) {
    var games by mutableStateOf<List<GameModel>>(emptyList())
    var isLoading by mutableStateOf(true)

    val request = APIClient.apiService.getResponse()
    request.enqueue(object : Callback<JsonObject> {
        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
            if (response.isSuccessful && response.body() != null) {
                // on success
                Log.d("LOCATION", "http call successfully")
                GameModelList.getGameData(response.body()!!)
                games = GameModelList.games
            } else {
                games = emptyList()
            }
            isLoading = false
            onResult(games)
        }

        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            // on connection error
            games = emptyList()
            isLoading = false
            onResult(games)
        }
    })
}
