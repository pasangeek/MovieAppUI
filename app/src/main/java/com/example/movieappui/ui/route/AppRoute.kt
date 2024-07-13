package com.example.movieappui.ui.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

object AppRoute {

    @Composable
    fun GenerateRoute(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = RouteName.Home,
        ) {
            composable(RouteName.Home) {

            }
            composable("${RouteName.Detail}/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")

            }
            composable(RouteName.SeatSelector) {

            }
        }
    }
}