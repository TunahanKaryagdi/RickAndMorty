package com.example.rickandmorty.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmorty.presentation.screens.detail.DetailScreen
import com.example.rickandmorty.presentation.screens.home.HomeScreen
import com.example.rickandmorty.presentation.screens.splash.SplashScreen


@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavigationItem.SplashScreen.route) {

        composable(NavigationItem.SplashScreen.route) {
            SplashScreen()
        }
        composable(NavigationItem.HomeScreen.route) {
            HomeScreen()
        }
        composable(NavigationItem.DetailScreen.route) {
            DetailScreen()
        }
    }


}


sealed class NavigationItem(val route: String) {

    object SplashScreen : NavigationItem("splash")
    object HomeScreen : NavigationItem("home")
    object DetailScreen : NavigationItem("detail")

}