package com.faiqaryadewangga.newsapp_coil.data.service.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first


val Context.datastore by preferencesDataStore(DatastoreUtils.DATASTORE_NAME)

class AppDatastore(context: Context) {

    private val datastore = context.datastore

    companion object {
        @Volatile
        private var INSTANCE: AppDatastore? = null

        fun getInstance(context: Context): AppDatastore {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AppDatastore(context).also { INSTANCE = it }
            }
        }
    }


    suspend fun saveOnboardStatus(isPassed: Boolean) {
        datastore.edit {
            it[DatastoreUtils.ONBOARDING_KEY] = isPassed
        }
    }

    suspend fun getOnboardStatus(): Boolean {
        return datastore.data.first()[DatastoreUtils.ONBOARDING_KEY] ?: false
    }
}