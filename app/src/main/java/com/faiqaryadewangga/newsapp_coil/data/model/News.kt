package com.faiqaryadewangga.newsapp_coil.data.model

data class News(
    val newsId: String,
    val title: String,
    val imageUrl: String,
    val isRecommended: Boolean,
    val source: String,
    val timestamp: String,
    val content: String,
)
