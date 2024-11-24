package com.faiqaryadewangga.newsapp_coil.data.service.firebase

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseService private constructor() {

    private val firestore by lazy { FirebaseFirestore.getInstance() }

    companion object {
        @Volatile
        private var instance: FirebaseService? = null

        fun getInstance(): FirebaseService {
            return instance ?: synchronized(this) {
                instance ?: FirebaseService().also { instance = it }
            }
        }
    }

    fun getCollection(collectionName: String) =
        firestore.collection(collectionName)
    fun getDocument(collectionName: String, documentId: String) =
        firestore.collection(collectionName).document(documentId)
}