package com.faiqaryadewangga.newsapp_coil.features.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.faiqaryadewangga.newsapp_coil.data.datasource.local.AppLocalDatasource
import com.faiqaryadewangga.newsapp_coil.data.datasource.local.AppLocalDatasourceFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(context: Context) : ViewModel() {

    private val appDatastoreFactory: AppLocalDatasourceFactory = AppLocalDatasourceFactory(context)
    private val appDatastore: AppLocalDatasource = appDatastoreFactory.createDatasource() as AppLocalDatasource

    private val _isPassedOnboard = MutableStateFlow(false)
    val isPassedOnboard = _isPassedOnboard.asStateFlow()

    private fun getOnboardStatus() {
        viewModelScope.launch {
            try {
                val isPassedOnboard = appDatastore.getOnboardStatus()
                _isPassedOnboard.value = isPassedOnboard
            } catch (e: Exception) {
                appDatastore.onError(e.message)
            }
        }
    }

    init {
        getOnboardStatus()
    }
}