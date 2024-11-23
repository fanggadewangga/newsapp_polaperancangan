package com.faiqaryadewangga.newsapp_coil.data.datasource.remote

import android.util.Log
import com.faiqaryadewangga.newsapp_coil.data.datasource.BaseDatasource
import com.faiqaryadewangga.newsapp_coil.data.model.News
import com.faiqaryadewangga.newsapp_coil.data.service.firebase.FirebaseService
import com.faiqaryadewangga.newsapp_coil.util.toNews
import kotlinx.coroutines.tasks.await

class RemoteDatasource : BaseDatasource {

    private val firebaseService = FirebaseService.getInstance()

    suspend fun getNews(): List<News> {
        return try {
            val newsDocs = firebaseService.getCollection("news").get().await()
            newsDocs.mapNotNull { it.toNews() }
        } catch (e: Exception) {
            onError(e.message)
            emptyList()
        }
    }

    suspend fun searchNews(query: String): List<News> {
        return try {
            val newsDocs = firebaseService.getCollection("news").get().await()
            newsDocs.mapNotNull { it.toNews() }
                .filter { it.title.contains(query, ignoreCase = true) }
        } catch (e: Exception) {
            onError(e.message)
            emptyList()
        }
    }

    suspend fun getNewsDetail(newsId: String): News? {
        return try {
            val newsDoc = firebaseService.getDocument("news", newsId).get().await()
            newsDoc.toNews()
        } catch (e: Exception) {
            onError(e.message)
            null
        }
    }

    override fun onError(message: String?) {
        Log.e("RemoteDatasource", "Error: $message")
    }
}