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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.faiqaryadewangga.newsapp_coil.R
import com.faiqaryadewangga.newsapp_coil.ui.theme.primary

@Composable
fun NewsDetailScreen(
    navController: NavController,
    newsId: String,
) {

    val context = LocalContext.current
    val viewModel = remember { NewsDetailViewModel(context) }
    val news by viewModel.news.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getNewsDetails(newsId)
    }

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
                    .background(color = Color.White)
                    .verticalScroll(rememberScrollState())
                    .testTag("Detail content")
                    .semantics { contentDescription = "Detail content" }
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = newsData.imageUrl,
                        contentDescription = "News Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(320.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 48.dp, bottom = 12.dp, end = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { navController.popBackStack() }
                        )
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(36.dp)
                                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                        ) {
                            Image(
                                painter = painterResource(id = if (newsData.isBookmarked) R.drawable.ic_bookmark_selected else R.drawable.ic_bookmark_unselected),
                                contentDescription = "Bookmark",
                                colorFilter = if (newsData.isBookmarked) ColorFilter.tint(primary) else ColorFilter.tint(
                                    Color.LightGray
                                ),
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        if (newsData.isBookmarked) viewModel.deleteBookmark(
                                            context,
                                            newsId
                                        )
                                        else viewModel.bookmarkNews(context, newsData)
                                    }
                            )
                        }
                    }


                }
                Spacer(Modifier.height(16.dp))
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
                    text = newsData.source,
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
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
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