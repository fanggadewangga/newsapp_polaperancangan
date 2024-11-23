package com.faiqaryadewangga.newsapp_coil.features.bookmarks

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faiqaryadewangga.newsapp_coil.data.datasource.local.NewsLocalDatasource
import com.faiqaryadewangga.newsapp_coil.data.datasource.local.NewsLocalDatasourceFactory
import com.faiqaryadewangga.newsapp_coil.data.model.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookmarkViewModel(context: Context) : ViewModel() {

    private val newsLocalDatasource by lazy {
        NewsLocalDatasourceFactory(context).createDatasource() as NewsLocalDatasource
    }

    private val _savedNews = MutableStateFlow<List<News>>(emptyList())
    val savedNews = _savedNews.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    private fun getSavedNews() {
        viewModelScope.launch {
            try {
                val newsList = newsLocalDatasource.getAllBookmarkedNews()
                _savedNews.value = newsList
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Terjadi Kesalahan"
            }
        }
    }

    init {
        getSavedNews()
    }
}