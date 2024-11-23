package com.faiqaryadewangga.newsapp_coil.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faiqaryadewangga.newsapp_coil.data.datasource.remote.RemoteDatasource
import com.faiqaryadewangga.newsapp_coil.data.datasource.remote.RemoteDatasourceFactory
import com.faiqaryadewangga.newsapp_coil.data.model.News
import com.faiqaryadewangga.newsapp_coil.util.toNews
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SearchViewModel : ViewModel() {

    private val remoteDatasource by lazy {
        RemoteDatasourceFactory().createDatasource() as RemoteDatasource
    }

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _news = MutableStateFlow<List<News>>(emptyList())
    val news = _news.asStateFlow()

    private val _popularNews = MutableStateFlow<List<News>>(emptyList())
    val popularNews = _popularNews.asStateFlow()

    private val _otherNews = MutableStateFlow<List<News>>(emptyList())
    val otherNews = _otherNews.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }

    private fun getNews() {
        viewModelScope.launch {
            try {
                val newsList = remoteDatasource.getNews()

                _popularNews.value = newsList.filter { it.isRecommended }.take(5)

                val remainingNews = newsList.filterNot { popularNews.value.contains(it) }
                _otherNews.value = remainingNews

            } catch (e: FirebaseFirestoreException) {
                _errorMessage.value = e.message ?: "Terjadi kesalahan"
            }
        }
    }

    fun searchNews() {
        viewModelScope.launch {
            try {
                val newsList = remoteDatasource.searchNews(query.value)
                _news.value = newsList
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Terjadi Kesalahan"
            }
        }
    }

    init {
        getNews()
    }
}