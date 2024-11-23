package com.faiqaryadewangga.newsapp_coil.features.bookmarks

import android.view.Surface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.faiqaryadewangga.newsapp_coil.R
import com.faiqaryadewangga.newsapp_coil.components.NewsItem
import com.faiqaryadewangga.newsapp_coil.navigation.Route
import com.faiqaryadewangga.newsapp_coil.ui.theme.primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen(navController: NavController) {

    val context = LocalContext.current
    val viewModel = remember { BookmarkViewModel(context) }
    val savedNews = viewModel.savedNews.collectAsStateWithLifecycle()


    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    colorFilter = ColorFilter.tint(primary),
                    modifier = Modifier
                        .padding(start = 16.dp, top = 24.dp, bottom = 12.dp)
                        .size(24.dp)
                        .clickable { navController.popBackStack() }
                        .align(Alignment.CenterStart)
                )
                Text(
                    text = "Tersimpan",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = primary
                    ),
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 12.dp)
                        .align(Alignment.Center)
                )
            }
        },
        modifier = Modifier
            .systemBarsPadding()
            .background(Color.White)
    ) {

        val topPadding = it.calculateTopPadding()
        val bottomPadding = it.calculateBottomPadding()

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
        )
        {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(top = topPadding + 24.dp, bottom = bottomPadding)
                    .navigationBarsPadding()
                    .systemBarsPadding()
            ) {
                items(savedNews.value) { news ->
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