package com.faiqaryadewangga.newsapp_coil.navigation

sealed class Route(val route: String) {
    data object Home: Route("home")
    data object Search: Route("search")
    data object NewsDetail : Route("detail/{newsId}") {
        fun createRoute(newsId: String) = "detail/$newsId"
    }
    data object Bookmarks: Route("bookmarks")
}