package com.example.dorean

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dorean.ui.theme.DoreanTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DoreanTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                ) {
                    innerPadding ->
                    val context = LocalContext.current
                    val cache = remember { GameCache(context) }

                    var games by remember { mutableStateOf<List<GameModel>>(emptyList()) }
                    var loading by remember { mutableStateOf(true) }

                    val scope = rememberCoroutineScope()
                    fun fetchData() {
                        scope.launch {
                            loading = true
                            Log.d("CACHED", "Fetching data online")
                            requestData { result, isLoading ->
                                games = result
                                loading = isLoading
                                if (!isLoading) cache.save(result)
                            }
                        }
                    }

                    LaunchedEffect(Unit) {
                        val cached = cache.load()
                        if (cached != null) {
                            games = cached
                            Log.d("CACHED", "got cached data")
                            loading=false
                        } else {
                            fetchData()
                        }
                    }

                    Box(modifier = Modifier.padding(innerPadding)) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                Text("Dorean", fontSize =45.sp)
                                Refresh { fetchData() }
                            }
                            GameCard.Show(games = games, loading= loading, modifier = Modifier)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Refresh(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(Icons.Rounded.Refresh,"refresh",
            Modifier.size(45.dp)
        )
    }
}
