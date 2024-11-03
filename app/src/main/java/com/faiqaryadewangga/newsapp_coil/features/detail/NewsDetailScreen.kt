package com.faiqaryadewangga.newsapp_coil.features.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.faiqaryadewangga.newsapp_coil.R

@Composable
fun NewsDetailScreen(
    navController: NavController,
    newsId: String,
) {

    val viewModel = viewModel<NewsDetailViewModel>()
    val news by viewModel.news.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getNewsDetails(newsId)
    }


    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
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
                        .padding(start = 16.dp, top = 12.dp, bottom = 12.dp)
                        .size(24.dp)
                        .clickable { navController.popBackStack() }
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_app_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .size(24.dp)
                )
            }
        }
    ) {

        val topPadding = it.calculateTopPadding()

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
            if (news != null) {
                val newsData = news!!
                val annotatedText = newsData.content.replace("\\n", "\n")
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = topPadding)
                        .background(color = Color.White)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = newsData.title,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                        ),
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "CNN Indonesia",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(600),
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = newsData.timestamp,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(600),
                            color = Color.Gray
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(Modifier.height(16.dp))
                    AsyncImage(
                        model = newsData.imageUrl,
                        contentDescription = "News Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(320.dp)
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = annotatedText,
                        style = TextStyle(fontSize = 12.sp),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}