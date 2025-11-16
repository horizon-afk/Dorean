package com.example.dorean

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage


object GameCard {

    @Composable
    fun Show(
        games : List<GameModel>,
        loading : Boolean,
        modifier: Modifier
    ) {

        //show a circular loading animation
        if (loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = Color(0xFF408EE0),

                    )
            }
        } else {
            LazyColumn(modifier = modifier) {
                items(games) { game ->
                    GameCard(game)
                }
            }
        }
    }


    @Composable
    private fun GameCard(game: GameModel) {
        Card(modifier = Modifier.padding(10.dp)) {
            Text(game.name, fontSize = 25.sp)
            AsyncImage(
                model = game.imageUrl,
                contentDescription = null,
            )
            Text(game.description)
            Text("Get before: ${game.endDate}")
        }
    }
}