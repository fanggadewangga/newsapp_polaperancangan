package com.faiqaryadewangga.newsapp_coil.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomAppBar
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FabPosition
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FloatingActionButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.faiqaryadewangga.newsapp_coil.R
import com.faiqaryadewangga.newsapp_coil.components.AppNavigationBar
import com.faiqaryadewangga.newsapp_coil.data.model.NavigationBarItem
import com.faiqaryadewangga.newsapp_coil.features.bookmarks.BookmarksScreen
import com.faiqaryadewangga.newsapp_coil.features.detail.NewsDetailScreen
import com.faiqaryadewangga.newsapp_coil.features.home.HomeScreen
import com.faiqaryadewangga.newsapp_coil.features.search.SearchScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val mainScreens = listOf(NavigationBarItem.Home, NavigationBarItem.Bookmarks)
    val isShowNavbar =
        mainScreens.any { it.route == currentDestination?.route } || currentDestination?.route == Route.Search.route
    LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    Scaffold(
        bottomBar = {
            if (isShowNavbar)
                BottomAppBar(
                    backgroundColor = Color.White,
                    contentPadding = PaddingValues(0.dp),
                    cutoutShape = CircleShape,
                    modifier = Modifier
                        .navigationBarsPadding()
                        .height((screenHeight * 0.07).dp)
                ) {
                    AppNavigationBar(
                        isShowNavbar = true,
                        items = mainScreens,
                        currentDestination = currentDestination,
                        onClick = { route ->
                            navController.navigate(route) {
                                popUpTo(navController.graph.id) {
                                    saveState = true
                                    inclusive = true
                                }
                                restoreState = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((screenHeight * 0.07).dp)
                    )
                }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            if (isShowNavbar) {
                FloatingActionButton(
                    shape = CircleShape,
                    backgroundColor = Color(0xFF283C73),
                    onClick = { navController.navigate("search") },
                    modifier = Modifier
                        .size(48.dp)
                        .testTag("Search icon")
                        .semantics { contentDescription = "Search icon" },
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Searchicon",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    ) {
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
                    NewsDetailScreen(navController = navController, newsId = it)
                }
            }

            composable(Route.Bookmarks.route) {
                BookmarksScreen(navController)
            }
        }
    }
}