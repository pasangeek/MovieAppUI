package com.example.movieappui.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieappui.model.nowPlayingMovie
import com.example.movieappui.ui.DetailScreen
import com.example.movieappui.ui.HomeScreen
import com.example.movieappui.ui.SeatSelecterScreen

object AppRoute {
    @Composable
    fun GenerateRoute(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = RouteName.Home,
        ) {
            composable(RouteName.Home) {
                HomeScreen(navController = navController)
            }
            composable("${RouteName.Detail}/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")
                val movie = nowPlayingMovie.first{ it.id == id }

                DetailScreen(navController = navController, movie)
            }
            composable(RouteName.SeatSelector) {
                SeatSelecterScreen(navController = navController)
            }
        }
    }
}