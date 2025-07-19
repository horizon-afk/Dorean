package com.example.dorean

import android.content.Context
import kotlinx.serialization.json.*
import java.io.File

class GameCache(context: Context) {
    private val file = File(context.filesDir, "games_cache.json")

    fun save(games: List<GameModel>) {
        val jsonString = Json.encodeToString(games)
        file.writeText(jsonString)
    }

    fun load(): List<GameModel>? {
        return runCatching {
            if (!file.exists()) return null
            val jsonString = file.readText()
            Json.decodeFromString<List<GameModel>>(jsonString)
        }.getOrNull()
    }
}