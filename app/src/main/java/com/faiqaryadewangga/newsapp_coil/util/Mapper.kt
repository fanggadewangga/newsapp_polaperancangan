package com.faiqaryadewangga.newsapp_coil.util

import android.util.Log
import com.faiqaryadewangga.newsapp_coil.data.model.News
import com.google.firebase.firestore.DocumentSnapshot

fun DocumentSnapshot.toNews(): News?{
    return try {
        News(
            newsId = getString("newsId") ?: "",
            title = getString("title") ?: "",
            imageUrl = getString("imageUrl") ?: "",
            isRecommended = getBoolean("isRecommended") ?: false,
            source = getString("source") ?: "",
            timestamp = getString("timestamp")?: "",
            content = getString("content") ?: ""
        )
    } catch (e: Exception) {
        Log.e("NewsMapper", "Error mapping document to News: ${e.message}")
        null
    }
}