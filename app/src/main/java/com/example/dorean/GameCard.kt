package com.example.dorean

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage


object GameCard {

    @Composable
    fun Show(modifier: Modifier) {
        val context = LocalContext.current
        val cache = remember { GameCache(context) }

        var games by remember { mutableStateOf<List<GameModel>>(emptyList()) }
        var loading by remember { mutableStateOf(true) }
        LaunchedEffect(Unit) {
            val cached = cache.load()
            if (cached != null) {
                games = cached
                Log.d("CACHED", "got cached data")
                loading=false
            } else {
                requestData { result, isLoading ->
                    games = result
                    Log.d("CACHED","fetching data online")
                    loading = isLoading
                    if (!isLoading) cache.save(result)
                }
            }
        }

        //show a circular loading animation
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = Color.Blue,
            )
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