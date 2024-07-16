package com.example.movieappui.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieappui.ui.HomeScreen

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

            }
            composable(RouteName.SeatSelector) {

            }
        }
    }
}