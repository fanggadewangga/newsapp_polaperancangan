package com.faiqaryadewangga.newsapp_coil.features.onboard

import android.content.Context
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faiqaryadewangga.newsapp_coil.data.datasource.local.AppLocalDatasource
import com.faiqaryadewangga.newsapp_coil.data.datasource.local.AppLocalDatasourceFactory
import kotlinx.coroutines.launch

class OnboardViewModel(context: Context): ViewModel() {

    private val appDatastoreFactory: AppLocalDatasourceFactory = AppLocalDatasourceFactory(context)
    private val appDatastore: AppLocalDatasource = appDatastoreFactory.createDatasource() as AppLocalDatasource

    var currentPage = mutableIntStateOf(0)
    var isLastPage = mutableStateOf(false)

    fun saveOnboardStatus() {
        viewModelScope.launch {
            try {
                appDatastore.saveOnboardStatus(true)
            } catch (e: Exception) {
                appDatastore.onError(e.message)
            }
        }
    }
}