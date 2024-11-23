package com.faiqaryadewangga.newsapp_coil.data.service.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.faiqaryadewangga.newsapp_coil.data.model.News

@Database(entities = [News::class], version = 1)
abstract class NewsDb : RoomDatabase() {
    abstract val newsDao: NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDb? = null

        fun getInstance(context: Context): NewsDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDb::class.java,
                    "news_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
