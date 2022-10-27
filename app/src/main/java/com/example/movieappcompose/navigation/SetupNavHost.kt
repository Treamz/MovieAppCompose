package com.example.movieappcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieappcompose.MainViewModel
import com.example.movieappcompose.screens.DetailsScreen
import com.example.movieappcompose.screens.MainScreen
import com.example.movieappcompose.screens.SplashScreen
import com.example.movieappcompose.utils.Constants


sealed class Screens(val route:String) {
    object Splash: Screens(route = Constants.Screens.SPLASH_SCREEN)
    object Main: Screens(route = Constants.Screens.MAIN_SCREEN)
    object Details: Screens(route = Constants.Screens.DETAIL_SCREEN)
}

@Composable
fun setupNavHost(navController: NavHostController,viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = Screens.Splash.route ) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navController,viewModel)
        }
        composable(route = Screens.Main.route) {
            MainScreen(navController,viewModel)
        }
        composable(route = Screens.Details.route + "/{id}") { navBackStackEntry ->
            DetailsScreen(viewModel = viewModel, itemId = navBackStackEntry.arguments?.getString("id") ?: "1")
        }
    }
}