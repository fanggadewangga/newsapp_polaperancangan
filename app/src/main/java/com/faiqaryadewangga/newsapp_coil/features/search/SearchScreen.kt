package com.faiqaryadewangga.newsapp_coil.features.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.faiqaryadewangga.newsapp_coil.R
import com.faiqaryadewangga.newsapp_coil.components.NewsItem
import com.faiqaryadewangga.newsapp_coil.navigation.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(navController: NavController) {

    val viewModel = viewModel<SearchViewModel>()
    val query = viewModel.query.collectAsStateWithLifecycle()
    val news = viewModel.news.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .systemBarsPadding()
                    .fillMaxWidth()
                    .background(color = Color.Black)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .size(24.dp)
                        .clickable { navController.popBackStack() }
                )
                BasicTextField(
                    value = query.value,
                    onValueChange = {
                        viewModel.onQueryChange(it)
                        scope.launch {
                            delay(2000L)
                            viewModel.searchNews()
                        }
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp, top = 12.dp, bottom = 12.dp)
                )
            }
        },
        modifier = Modifier.background(Color.White)
    ) {
        val topPadding = it.calculateTopPadding()

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
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White)
                        .padding(top = topPadding, start = 16.dp, end = 16.dp)
                ) {
                    items(news.value) { news ->
                        NewsItem(
                            news = news,
                            onClick = { newsId ->
                                navController.navigate(Route.NewsDetail.createRoute(newsId))
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(110.dp)
                        )
                    }
                }
            }
        }
    }
}