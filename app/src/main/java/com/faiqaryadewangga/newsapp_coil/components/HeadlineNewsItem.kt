package com.faiqaryadewangga.newsapp_coil.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.faiqaryadewangga.newsapp_coil.R
import com.faiqaryadewangga.newsapp_coil.data.model.News

@Composable
fun HeadlineNewsItem(
    modifier: Modifier = Modifier,
    news: News,
    onClick: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.clickable { onClick(news.newsId) }
    ) {
        AsyncImage(
            model = news.imageUrl,
            contentDescription = "News Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(Modifier.height(4.dp))
        Text(
            news.timestamp,
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF868686),
            ),
        )
        Text(
            news.title,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            news.content,
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF3D3D3D),
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}