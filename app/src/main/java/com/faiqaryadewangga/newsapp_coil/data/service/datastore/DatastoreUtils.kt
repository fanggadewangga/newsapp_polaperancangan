package com.faiqaryadewangga.newsapp_coil.data.service.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey

object DatastoreUtils {
    const val DATASTORE_NAME = "app_datastore"
    val ONBOARDING_KEY = booleanPreferencesKey("onboarding_key")
}