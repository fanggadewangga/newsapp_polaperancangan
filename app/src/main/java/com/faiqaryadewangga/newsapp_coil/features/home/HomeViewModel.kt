package com.faiqaryadewangga.newsapp_coil.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faiqaryadewangga.newsapp_coil.data.model.News
import com.faiqaryadewangga.newsapp_coil.util.toNews
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeViewModel : ViewModel() {

    private val _headlineNews = MutableStateFlow<List<News>>(emptyList())
    val headlineNews = _headlineNews.asStateFlow()

    private val _latestNews = MutableStateFlow<List<News>>(emptyList())
    val latestNews = _latestNews.asStateFlow()

    private val _otherNews = MutableStateFlow<List<News>>(emptyList())
    val otherNews = _otherNews.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    private fun getNews() {
        viewModelScope.launch {
            try {
                val firestore = FirebaseFirestore.getInstance()
                val newsDocs = firestore.collection("news").get().await()

                val newsList = newsDocs.map { it.toNews() }

                _headlineNews.value = newsList.filter { it.isRecommended }.take(3)

                val remainingNews = newsList.filterNot { headlineNews.value.contains(it) }
                _latestNews.value = remainingNews.take(4)
                _otherNews.value = remainingNews.drop(4)

            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Terjadi kesalahan"
            }
        }
    }

    init {
        getNews()
    }
}