package com.wcisang.jetpackcomposemovies.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wcisang.domain.model.Movie
import com.wcisang.jetpackcomposemovies.feature.detail.DetailScreenView
import com.wcisang.jetpackcomposemovies.feature.main.MainScreenView
import com.wcisang.jetpackcomposemovies.feature.splash.SplashScreenView

sealed class ScreenMain(val route: String) {
    object Splash : ScreenMain("splash")
    object Main : ScreenMain("main")
    object Detail : ScreenMain("detail")
}

@ExperimentalFoundationApi
@Composable
fun MovieNavigationApplication() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenMain.Main.route) {
        composable(ScreenMain.Splash.route) {
            SplashScreenView()
        }
        composable(ScreenMain.Main.route) {
            MainScreenView(navController)
        }
        composable(ScreenMain.Detail.route) {
            val movie = navController
                .previousBackStackEntry
                ?.arguments
                ?.getParcelable<Movie>("movie")

            DetailScreenView(
                navController = navController,
                viewModel = hiltNavGraphViewModel(),
                movie = movie!!
            )
        }
    }
}