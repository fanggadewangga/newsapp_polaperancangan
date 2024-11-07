package com.faiqaryadewangga.newsapp_coil.util

import com.faiqaryadewangga.newsapp_coil.data.model.News
import com.google.firebase.firestore.DocumentSnapshot

fun DocumentSnapshot.toNews(): News {
    val newsId = getString("newsId") ?: throw IllegalArgumentException("Field 'newsId' tidak ditemukan.")
    val title = getString("title") ?: throw IllegalArgumentException("Field 'title' tidak ditemukan.")
    val imageUrl = getString("imageUrl") ?: throw IllegalArgumentException("Field 'imageUrl' tidak ditemukan.")
    val isRecommended = getBoolean("isRecommended") ?: throw IllegalArgumentException("Field 'isRecommended' tidak ditemukan.")
    val source = getString("source") ?: throw IllegalArgumentException("Field 'source' tidak ditemukan.")
    val timestamp = getString("timestamp") ?: throw IllegalArgumentException("Field 'timestamp' tidak ditemukan.")
    val content = getString("content") ?: throw IllegalArgumentException("Field 'content' tidak ditemukan.")

    return News(
        newsId = newsId,
        title = title,
        imageUrl = imageUrl,
        isRecommended = isRecommended,
        source = source,
        timestamp = timestamp,
        content = content
    )
}
