package com.faiqaryadewangga.newsapp_coil.features.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.faiqaryadewangga.newsapp_coil.R
import com.faiqaryadewangga.newsapp_coil.components.LatestNewsItem
import com.faiqaryadewangga.newsapp_coil.components.NewsItem
import com.faiqaryadewangga.newsapp_coil.navigation.Route

@Composable
fun SearchScreen(navController: NavController) {

    val viewModel = viewModel<SearchViewModel>()
    val query = viewModel.query.collectAsStateWithLifecycle()
    val news = viewModel.news.collectAsStateWithLifecycle()
    rememberCoroutineScope()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val popularNews by viewModel.popularNews.collectAsStateWithLifecycle()
    val otherNews by viewModel.otherNews.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                if (query.value.isNotBlank()) {
                    Icon(
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = "Back",
                        tint = Color(0xFF283C73),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { viewModel.onQueryChange("") }
                    )
                }
                OutlinedTextField(
                    value = query.value,
                    onValueChange = viewModel::onQueryChange,
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            viewModel.searchNews()
                        }
                    ),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    placeholder = {
                        Text(
                            text = "Cari berita yang ingin dibaca",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF9E9E9E),
                            ),
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF0D0D0D),
                    ),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = "Search",
                            tint = Color(0xFF283C73),
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF2F4F7),
                        unfocusedContainerColor = Color(0xFFF2F4F7),
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .systemBarsPadding()
                        .weight(1f)
                )
            }
        },
        modifier = Modifier.background(Color.White)
    ) {
        val topPadding = it.calculateTopPadding()
        val bottomPadding = it.calculateBottomPadding()

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
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
                        .padding(top = topPadding - 24.dp, bottom = bottomPadding)
                        .navigationBarsPadding()
                ) {
                    if (query.value.isBlank()) {
                        item {
                            Text(
                                "Berita Populer",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                    fontWeight = FontWeight(600),
                                    color = Color.Black
                                ),
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    bottom = 12.dp
                                )
                            )
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                                    .background(Color.White)
                            ) {
                                items(popularNews) { news ->
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
                        item {
                            Text(
                                "Daftar Berita",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                    fontWeight = FontWeight(600),
                                    color = Color.Black
                                ),
                                modifier = Modifier.padding(
                                    top = 24.dp,
                                    start = 16.dp,
                                    bottom = 12.dp
                                )
                            )
                        }
                        items(otherNews) { news ->
                            NewsItem(
                                news = news,
                                onClick = { newsId ->
                                    navController.navigate(
                                        Route.NewsDetail.createRoute(
                                            newsId
                                        )
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(110.dp)
                                    .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                            )
                        }
                    } else {
                        items(news.value) { news ->
                            NewsItem(
                                news = news,
                                onClick = { newsId ->
                                    navController.navigate(
                                        Route.NewsDetail.createRoute(
                                            newsId
                                        )
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(110.dp)
                                    .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}