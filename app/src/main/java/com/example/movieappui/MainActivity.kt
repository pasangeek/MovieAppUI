package com.example.movieappui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.movieappui.route.AppRoute
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
                AppRoute.GenerateRoute(navController = navController)
            }
        }
    }
}