package com.faiqaryadewangga.newsapp_coil.data.model

import androidx.annotation.DrawableRes
import com.faiqaryadewangga.newsapp_coil.R
import com.faiqaryadewangga.newsapp_coil.navigation.Route

sealed class NavigationBarItem(
    val route: String,
    val title: String?,
    @DrawableRes val icon: Int,
) {
    data object Home : NavigationBarItem(
        route = Route.Home.route,
        title = "Beranda",
        icon = R.drawable.ic_home_unselected
    )

    data object Profile : NavigationBarItem(
        route = Route.Profile.route,
        title = "Profil",
        icon = R.drawable.ic_profile_unselected
    )
}