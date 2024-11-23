package com.faiqaryadewangga.newsapp_coil.features.detail

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faiqaryadewangga.newsapp_coil.data.datasource.local.NewsLocalDatasource
import com.faiqaryadewangga.newsapp_coil.data.datasource.local.NewsLocalDatasourceFactory
import com.faiqaryadewangga.newsapp_coil.data.datasource.remote.RemoteDatasource
import com.faiqaryadewangga.newsapp_coil.data.datasource.remote.RemoteDatasourceFactory
import com.faiqaryadewangga.newsapp_coil.data.model.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsDetailViewModel(context: Context) : ViewModel() {

    private val remoteDatasource by lazy {
        RemoteDatasourceFactory().createDatasource() as RemoteDatasource
    }

    private val newsLocalDatasource by lazy {
        NewsLocalDatasourceFactory(context).createDatasource() as NewsLocalDatasource
    }

    private val _news = MutableStateFlow<News?>(null)
    val news = _news.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun getNewsDetails(newsId: String) {
        viewModelScope.launch {
            try {
                val newsDetail = remoteDatasource.getNewsDetail(newsId)

                if (newsDetail != null) {
                    val isBookmarked = newsLocalDatasource.isNewsBookmarked(newsId)
                    _news.value = newsDetail.copy(isBookmarked = isBookmarked)
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Terjadi kesalahan"
            }
        }
    }

    fun bookmarkNews(context: Context, news: News) {
        viewModelScope.launch {
            try {
                newsLocalDatasource.bookmarkNews(news)
                Toast.makeText(context, "Berhasil menambah bookmark", Toast.LENGTH_SHORT).show()
                _news.value = news.copy(isBookmarked = true)
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Terjadi kesalahan"
                Toast.makeText(context, errorMessage.value, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun deleteBookmark(context: Context, newsId: String) {
        viewModelScope.launch {
            try {
                newsLocalDatasource.removeBookmark(newsId)
                Toast.makeText(context, "Berhasil menghapus bookmark", Toast.LENGTH_SHORT).show()
                _news.value = news.value?.copy(isBookmarked = false)
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Terjadi kesalahan"
                Toast.makeText(context, errorMessage.value, Toast.LENGTH_SHORT).show()
            }
        }
    }
}