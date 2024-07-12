package com.example.movieappui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.movieappui.ui.theme.MovieAppUITheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /// from Accompanist, setting a status bar
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(
                color = Color.Transparent,
                darkIcons = true,
            )
            systemUiController.setNavigationBarColor(
                color = Color.Transparent,
                darkIcons = true,
            )

            MovieAppUITheme {
                /// main navigation
                val navController = rememberNavController()
            }
        }
    }
}