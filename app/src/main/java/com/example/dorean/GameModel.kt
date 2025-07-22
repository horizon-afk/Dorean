package com.example.dorean

import kotlinx.serialization.Serializable

@Serializable
data class GameModel(
    val name: String,
    val imageUrl: String,
    val description: String,
    val endDate: String
)
