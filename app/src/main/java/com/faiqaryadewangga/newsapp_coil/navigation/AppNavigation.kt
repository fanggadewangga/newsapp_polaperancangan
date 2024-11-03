package com.faiqaryadewangga.newsapp_coil.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.faiqaryadewangga.newsapp_coil.features.detail.NewsDetailScreen
import com.faiqaryadewangga.newsapp_coil.features.home.HomeScreen
import com.faiqaryadewangga.newsapp_coil.features.search.SearchScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    NavHost(
        navController = navController,
        startDestination = Route.Home.route,
    ) {
        composable(Route.Home.route) {
            HomeScreen(navController)
        }

        composable(Route.Search.route) {
            SearchScreen(navController)
        }

        composable(Route.NewsDetail.route) { navBackStackEntry ->
            val newsId = navBackStackEntry.arguments?.getString("newsId")
            newsId?.let {
                NewsDetailScreen(
                    navController = navController,
                    newsId = it
                )
            }
        }
    }
}