package com.wcisang.jetpackcomposemovies.feature.main

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.wcisang.jetpackcomposemovies.R
import com.wcisang.jetpackcomposemovies.feature.favorites.FavoriteScreenView
import com.wcisang.jetpackcomposemovies.feature.home.HomeScreenView

sealed class ScreenHome(val route: String, val icon: ImageVector, @StringRes val title: Int) {
    object Home : ScreenHome("home", Icons.Default.Home, R.string.tab_home)
    object Favorites : ScreenHome("favorites", Icons.Default.Star, R.string.tab_favorites)
}

private val navItems = listOf(ScreenHome.Home, ScreenHome.Favorites)

@ExperimentalFoundationApi
@Composable
fun MainScreenView(mainNavController: NavHostController?) {
    val navController = rememberNavController()
    val screenState = remember { mutableStateOf<ScreenHome>(ScreenHome.Home) }

    Scaffold(
        bottomBar = { MoviesBottomBar(navController) }
    ) {
        NavHost(navController, startDestination = ScreenHome.Home.route) {
            composable(ScreenHome.Home.route) {
                screenState.value = ScreenHome.Home
                HomeScreenView(mainNavController, hiltNavGraphViewModel())
            }
            composable(ScreenHome.Favorites.route) {
                screenState.value = ScreenHome.Favorites
                FavoriteScreenView(mainNavController, hiltNavGraphViewModel())
            }
        }
    }
}

@Composable
private fun MoviesBottomBar(navController: NavHostController) {
    BottomNavigation(backgroundColor = Color.Black, contentColor = Color.White) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
        navItems.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, "") },
                label = { Text(stringResource(screen.title)) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo = navController.graph.startDestination
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
