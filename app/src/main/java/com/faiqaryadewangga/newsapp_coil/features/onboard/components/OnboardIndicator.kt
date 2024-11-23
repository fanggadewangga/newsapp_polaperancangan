package com.faiqaryadewangga.newsapp_coil.features.onboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.faiqaryadewangga.newsapp_coil.ui.theme.primary

@Composable
fun OnboardIndicator(pagerState: PagerState, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(24.dp)
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val isActive = pagerState.currentPage == iteration
            val color = if (isActive) primary else Color.LightGray
            val shape = if (isActive) RoundedCornerShape(12.dp) else CircleShape
            val width = if (isActive) 14.dp else 32.dp

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(shape)
                    .background(color)
                    .height(14.dp)
                    .width(width)
            )
        }
    }
}