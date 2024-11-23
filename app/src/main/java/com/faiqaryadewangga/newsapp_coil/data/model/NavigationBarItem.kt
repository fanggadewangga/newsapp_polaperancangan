package com.faiqaryadewangga.newsapp_coil.data.model

import androidx.annotation.DrawableRes
import com.faiqaryadewangga.newsapp_coil.R
import com.faiqaryadewangga.newsapp_coil.navigation.Route

sealed class NavigationBarItem(
    val route: String,
    val title: String?,
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
) {
    data object Home : NavigationBarItem(
        route = Route.Home.route,
        title = "Beranda",
        unselectedIcon = R.drawable.ic_home_unselected,
        selectedIcon = R.drawable.ic_home_selected
    )

    data object Bookmarks : NavigationBarItem(
        route = Route.Bookmarks.route,
        title = "Tersimpan",
        unselectedIcon = R.drawable.ic_bookmark_unselected,
        selectedIcon = R.drawable.ic_bookmark_selected
    )
}