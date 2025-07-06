package com.example.dorean

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


object GameCard {


    @Composable
    fun Show(modifier: Modifier){

        var games by remember { mutableStateOf<List<GameModel>>(emptyList()) }

        LaunchedEffect(Unit) {
            requestData { result ->
                games = result
            }
        }

        LazyColumn(modifier = modifier) {
            items(games) { game ->
                GameCard(game)
            }
        }


    }

    @Composable
    private fun GameCard(game: GameModel) {
        Card {
            Text(game.name)
            Text(game.description)
            Text("Get before: ${game.endDate}")
        }
    }
}