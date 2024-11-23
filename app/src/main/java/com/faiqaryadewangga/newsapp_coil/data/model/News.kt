package com.faiqaryadewangga.newsapp_coil.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(
    @PrimaryKey
    val newsId: String,
    val title: String,
    val imageUrl: String,
    val isRecommended: Boolean,
    val source: String,
    val timestamp: String,
    val content: String,
    val isBookmarked: Boolean = false,
)
