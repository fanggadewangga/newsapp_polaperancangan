package com.faiqaryadewangga.newsapp_coil.features.onboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.faiqaryadewangga.newsapp_coil.features.onboard.components.OnboardIndicator
import com.faiqaryadewangga.newsapp_coil.features.onboard.components.OnboardItem
import com.faiqaryadewangga.newsapp_coil.navigation.Route
import com.faiqaryadewangga.newsapp_coil.ui.theme.primary

@Composable
fun OnboardScreen(navController: NavController) {

    val context = LocalContext.current
    val viewModel = remember { OnboardViewModel(context) }
    val currentPage by viewModel.currentPage
    val isLastPage by viewModel.isLastPage
    val pages = listOf(
        OnboardItem.FirstOnboard,
        OnboardItem.SecondOnboard,
    )
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val pagerState = rememberPagerState(
        initialPage = currentPage,
        pageCount = { pages.size }
    )

    // Update current page only when the page changes
    LaunchedEffect(key1 = currentPage) {
        pagerState.animateScrollToPage(currentPage)
    }

    LaunchedEffect(key1 = pagerState.currentPage) {
        val isLast = pagerState.currentPage == 1
        viewModel.currentPage.intValue = pagerState.currentPage
        viewModel.isLastPage.value = isLast
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.height((screenHeight * 0.78).dp)
            ) { page ->
                OnboardItem(item = pages[page], screenHeight = screenHeight)
            }
            Spacer(Modifier.height(24.dp))
            OnboardIndicator(pagerState = pagerState)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = (screenHeight * 0.06).dp)
        ) {
            if (pagerState.currentPage != pages.size - 1) {
                Text(
                    text = "Lewati",
                    color = primary,
                    modifier = Modifier.clickable {
                        viewModel.currentPage.intValue = pages.size - 1
                    }
                )
            }
            Button(
                onClick = {
                    if (isLastPage) {
                        viewModel.saveOnboardStatus()
                        navController.navigate(Route.Home.route) {
                            popUpTo(Route.Onboard.route) {
                                inclusive = true
                            }
                        }
                    } else {
                        val nextPage = currentPage + 1
                        val isLast = nextPage == 1
                        viewModel.currentPage.intValue = if (nextPage <= 1) nextPage else 1
                        viewModel.isLastPage.value = isLast
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = primary),
                contentPadding = PaddingValues(horizontal = 36.dp, vertical = 12.dp),
                modifier = Modifier.then(
                    if (pagerState.currentPage == pages.size - 1) Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp) else Modifier
                )
            ) {
                Text(
                    text = if (isLastPage) "Mulai" else "Lanjut",
                    color = Color.White
                )
            }
        }
    }

}