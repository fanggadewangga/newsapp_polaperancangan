package com.faiqaryadewangga.newsapp_coil.features.detail

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

class NewsDetailViewModel : ViewModel() {

    private val _news = MutableStateFlow<News?>(null)
    val news = _news.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun getNewsDetails(newsId: String) {
        viewModelScope.launch {
            try {
                val firestore = FirebaseFirestore.getInstance()
                val newsDoc = firestore.collection("news")
                    .document(newsId)
                    .get()
                    .await()
                _news.value = newsDoc.toNews()
            } catch (e: FirebaseFirestoreException) {
                _errorMessage.value = e.message ?: "Terjadi kesalahan"
            }
        }
    }
}