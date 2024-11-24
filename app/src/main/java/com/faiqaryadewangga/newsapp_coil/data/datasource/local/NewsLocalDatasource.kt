package com.faiqaryadewangga.newsapp_coil.data.datasource.local

import android.content.Context
import android.util.Log
import com.faiqaryadewangga.newsapp_coil.data.datasource.BaseDatasource
import com.faiqaryadewangga.newsapp_coil.data.model.News
import com.faiqaryadewangga.newsapp_coil.data.service.room.NewsDao
import com.faiqaryadewangga.newsapp_coil.data.service.room.NewsDb

class NewsLocalDatasource(private val context: Context) : BaseDatasource {

    private val newsDb: NewsDb by lazy { NewsDb.getInstance(context) }
    private val newsDao: NewsDao by lazy { newsDb.newsDao }

    override fun onError(message: String?) {
        Log.e("LocalDatasource", "Error: $message")
    }

    suspend fun bookmarkNews(news: News) {
        try {
            newsDao.insertNews(news)
        } catch (e: Exception) {
            onError(e.message)
        }
    }

    suspend fun getAllBookmarkedNews(): List<News> {
        return try {
            newsDao.getAllNews()
        } catch (e: Exception) {
            onError(e.message)
            emptyList()
        }
    }

    suspend fun removeBookmark(newsId: String) {
        try {
            newsDao.deleteNewsById(newsId)
        } catch (e: Exception) {
            onError(e.message)
        }
    }

    suspend fun isNewsBookmarked(newsId: String): Boolean {
        try {
            return newsDao.isNewsExists(newsId)
        } catch (e: Exception) {
            onError(e.message)
        }
        return false
    }
}

