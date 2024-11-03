package com.faiqaryadewangga.newsapp_coil.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faiqaryadewangga.newsapp_coil.data.model.News
import com.faiqaryadewangga.newsapp_coil.util.toNews
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SearchViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _news = MutableStateFlow<List<News>>(emptyList())
    val news = _news.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }

    fun searchNews() {
        viewModelScope.launch {
            try {
                val newsDocs = if (query.value.isBlank()) {
                    firestore.collection("news").get().await()
                } else {
                    firestore.collection("news")
                        .whereGreaterThanOrEqualTo("title", query)
                        .whereLessThanOrEqualTo("title", query)
                        .get()
                        .await()
                }

                val newsList = newsDocs.mapNotNull { it.toNews() }
                _news.value = newsList

            } catch (e: FirebaseFirestoreException) {
                _errorMessage.value = e.message ?: "Terjadi Kesalahan"
            }
        }
    }
}