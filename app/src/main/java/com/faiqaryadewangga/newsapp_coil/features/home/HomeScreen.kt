package com.faiqaryadewangga.newsapp_coil.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.faiqaryadewangga.newsapp_coil.R
import com.faiqaryadewangga.newsapp_coil.components.HeadlineNewsItem
import com.faiqaryadewangga.newsapp_coil.components.LatestNewsItem
import com.faiqaryadewangga.newsapp_coil.components.NewsItem
import com.faiqaryadewangga.newsapp_coil.navigation.Route

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = viewModel<HomeViewModel>()
    val headlineNews by viewModel.headlineNews.collectAsStateWithLifecycle()
    val latestNews by viewModel.latestNews.collectAsStateWithLifecycle()
    val otherNews by viewModel.otherNews.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()

    Scaffold {
        val topPadding = it.calculateTopPadding()
        val bottomPadding = it.calculateBottomPadding()

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF2F4F7))
        ) {
            if (errorMessage.isNotBlank()) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = errorMessage,
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight(600)),
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(top = topPadding, bottom = bottomPadding + 72.dp)
                ) {

                    // Headline News
                    item {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(horizontal = 16.dp, vertical = 24.dp)
                        ) {
                            items(headlineNews) { news ->
                                HeadlineNewsItem(
                                    news = news,
                                    onClick = { newsId ->
                                        navController.navigate(
                                            Route.NewsDetail.createRoute(
                                                newsId
                                            )
                                        )
                                    },
                                    modifier = Modifier
                                        .width(328.dp)
                                        .padding(end = 16.dp)
                                )
                            }
                        }
                    }

                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .background(color = Color(0xFFF2F4F7))
                        )
                    }

                    // Latest news
                    item {
                        Text(
                            "Berita Terbaru",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                fontWeight = FontWeight(600),
                                color = Color.Black
                            ),
                            modifier = Modifier.padding(top = 24.dp, start = 16.dp, bottom = 12.dp)
                        )
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                                .background(Color.White)
                        ) {
                            items(latestNews) { news ->
                                LatestNewsItem(
                                    news = news,
                                    onClick = { newsId ->
                                        navController.navigate(
                                            Route.NewsDetail.createRoute(
                                                newsId
                                            )
                                        )
                                    },
                                    modifier = Modifier
                                        .width(280.dp)
                                        .height(170.dp)
                                        .padding(end = 8.dp)
                                )
                            }
                        }
                    }

                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .background(color = Color(0xFFF2F4F7))
                        )
                    }

                    // Other news
                    item {
                        Text(
                            "Daftar Berita",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                fontWeight = FontWeight(600),
                                color = Color.Black
                            ),
                            modifier = Modifier.padding(top = 24.dp, start = 16.dp, bottom = 12.dp)
                        )
                    }

                    items(otherNews) { news ->
                        NewsItem(
                            news = news,
                            onClick = { newsId ->
                                navController.navigate(Route.NewsDetail.createRoute(newsId))
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(110.dp)
                                .padding(horizontal = 16.dp)
                        )
                        Spacer(Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}
