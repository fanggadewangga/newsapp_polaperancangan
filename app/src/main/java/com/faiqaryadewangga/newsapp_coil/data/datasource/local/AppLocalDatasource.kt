package com.faiqaryadewangga.newsapp_coil.data.datasource.local

import android.content.Context
import android.util.Log
import com.faiqaryadewangga.newsapp_coil.data.datasource.BaseDatasource
import com.faiqaryadewangga.newsapp_coil.data.service.datastore.AppDatastore

class AppLocalDatasource(context: Context): BaseDatasource {

    private val datastore = AppDatastore.getInstance(context)

    override fun onError(message: String?) {
        Log.e("LocalDatasource", "Error: $message")
    }

    suspend fun saveOnboardStatus(isPassed: Boolean) {
        datastore.saveOnboardStatus(isPassed)
    }

    suspend fun getOnboardStatus(): Boolean {
        return datastore.getOnboardStatus()
    }
}