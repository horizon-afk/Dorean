package com.example.dorean

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dorean.ui.theme.DoreanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DoreanTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                ) {
                    innerPadding ->

                    Box(modifier = Modifier.padding(innerPadding)) {
                        Column {
                            Text("Dorean", fontSize =50.sp, modifier = Modifier.padding(10.dp))
                            GameCard.Show(modifier = Modifier)
                        }

                    }


                }
            }
        }
    }
}
