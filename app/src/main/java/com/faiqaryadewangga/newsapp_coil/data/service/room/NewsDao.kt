package com.faiqaryadewangga.newsapp_coil.data.service.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faiqaryadewangga.newsapp_coil.data.model.News

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: News)

    @Query("SELECT * FROM News")
    suspend fun getAllNews(): List<News>

    @Query("DELETE FROM News WHERE newsId = :newsId")
    suspend fun deleteNewsById(newsId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM News WHERE newsId = :newsId)")
    suspend fun isNewsExists(newsId: String): Boolean
}