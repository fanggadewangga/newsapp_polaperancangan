package com.faiqaryadewangga.newsapp_coil.data.datasource.local

import android.content.Context
import com.faiqaryadewangga.newsapp_coil.data.datasource.BaseDatasource
import com.faiqaryadewangga.newsapp_coil.data.datasource.DatasourceFactory

class AppLocalDatasourceFactory(private val context: Context) : DatasourceFactory() {
    override fun createDatasource(): BaseDatasource {
        return AppLocalDatasource(context)
    }
}