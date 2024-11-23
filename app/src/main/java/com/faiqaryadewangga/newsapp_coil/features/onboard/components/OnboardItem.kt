package com.faiqaryadewangga.newsapp_coil.features.onboard.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.faiqaryadewangga.newsapp_coil.R

sealed class OnboardItem(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
) {
    data object FirstOnboard : OnboardItem(
        image = R.drawable.img_first_onboard,
        title = "Selalu Update dengan Berita Terbaru",
        description = "Temukan berita terkini yang relevan dan pastinya tidak ketinggalan tren paling update",
    )

    data object SecondOnboard : OnboardItem(
        image = R.drawable.img_second_onboard,
        title = "Simpan Berita dan Baca Kapan Saja",
        description = "Nikmati kemudahan membaca berita dengan fitur simpan yang memungkinkan Anda membacanya kapan saja",
    )
}

@Composable
fun OnboardItem(
    item: OnboardItem,
    screenHeight: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = (screenHeight * 0.15).dp, end = 16.dp)
    ) {
        AsyncImage(
            model = item.image,
            contentDescription = "${item.title} image",
            modifier = Modifier.width((screenHeight * 0.45).dp)
        )
        Text(
            text = item.title,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF252525),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 38.dp)
        )
        Text(
            text = item.description,
            textAlign = TextAlign.Center,style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF252525),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 34.dp)
        )
    }
}