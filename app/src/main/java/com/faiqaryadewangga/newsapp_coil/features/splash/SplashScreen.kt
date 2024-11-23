package com.faiqaryadewangga.newsapp_coil.features.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.faiqaryadewangga.newsapp_coil.R
import com.faiqaryadewangga.newsapp_coil.navigation.Route
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val context = LocalContext.current
    val viewModel = remember { SplashViewModel(context) }
    val isPassedOnboard = viewModel.isPassedOnboard.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        delay(2000)
        if (isPassedOnboard.value) {
            navController.navigate(Route.Home.route) {
                popUpTo(Route.Splash.route) { inclusive = true }
            }
            return@LaunchedEffect
        } else {
            navController.navigate(Route.Onboard.route) {
                popUpTo(Route.Splash.route) { inclusive = true }
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = R.drawable.img_splash,
            contentScale = ContentScale.Crop,
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize()
        )
    }
}